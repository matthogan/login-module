/**
 * 
 */
package com.softhog.security.loginmodule.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softhog.security.loginmodule.domain.CifResponse;
import com.softhog.security.loginmodule.domain.KVException;
import com.softhog.security.loginmodule.domain.LdapResponse;
import com.softhog.security.loginmodule.domain.LdapStaff;
import com.softhog.security.loginmodule.domain.ProxyException;

import com.softhog.dsl.ldapv3.proxy.SignonUserProxy;
import com.softhog.dsl.proxy.CommsException;

/**
 * Proxying the LDAP calls.
 * <p>
 * Note re signon: Always fails in test. Signon data is stored
 * on a separate server to the 
 * user/roles data so the 
 * password check always fails. Works in production.
 * <p>
 * Test does not check the password.
 * 
 * @author
 * 
 */
public class SignonServiceImpl
{
	/** */
	private Logger logger = LoggerFactory.getLogger( SignonServiceImpl.class );
    /** */
    private ConfigServiceImpl configService = new ConfigServiceImpl();

	/**
     * 
     */
	public SignonServiceImpl()
	{
	}

	/**
	 * 
	 */
	public void signon( LdapStaff staff )
	{
		logger.debug( "Signing on to ldap " + staff );
		
		//staff.setNsc( configService.getDefaultNsc() );
		// fatal on failure to signon for whatever reason
		// including test always failing issue - no point attempting in test
		if( configService.isProduction() )
		{				
			directoryServiceSignon( staff );
		}

		loadLdapUserDetails( staff );
		
		if( !containsRole( staff ) )
		{
			throw new KVException( "INV_LDAP_ROLE", "" + staff );
		}
	}
	
	/**
	 * @param staff
	 */
	private boolean containsRole( LdapStaff staff )
	{
        String role = staff.getLdapRole();
		
		List<String> ldap = staff.getRole();
		
		int len = ldap.size();
		// backwards as the role will be last, typically
		while( --len >= 0 )
		{
			if( StringUtils.startsWith( ldap.get( len ), role ) )
			{
				return true;				
			}
		}
		
		return false;
	}

	/**
	 * Uses the DSL/BUS code to signon.
	 * 
	 * @param staff - username, password
	 * @throws ProxyException when signon fails
	 */
	private void directoryServiceSignon( LdapStaff staff )
	{
		logger.debug( "Signon " + staff );
		
		try
		{	
            Proxy proxy = new Proxy();
            
			SignonUserProxy p = proxy.getProxy( "com.softhog.dsl.ldapv3.proxy.SignonUserProxy", staff.getWorkforceId() );
			
			String xml = p.signonUser( staff.getPassword(), true );
			
			CifResponse cif = proxy.unmarshall( xml, "com/softhog/security/loginmodule/service/proxy/eclipselink-oxm-signonuser.xml",
							"com.softhog.security.loginmodule.domain" );
			
			proxy.throwExceptionIfErrorCodeSet( cif.getErrorNode() );
		}
		catch( CommsException ex )
		{
			throw new ProxyException( "Signon failure. " + staff + ex.getMessage(), ex );
		}
	}

	/**
	 * @param staff - modifies
	 */
	private void loadLdapUserDetails( LdapStaff staff )
	{
		logger.debug( "Signon " + staff );

		try
		{
			Proxy proxy = new Proxy();
			
            SignonUserProxy p = proxy.getProxy( "com.softhog.dsl.ldapv3.proxy.SignonUserProxy", staff.getWorkforceId() );
			
			String xml = p.signonUser( null, null, false, "NP" );
			
			LdapResponse cif = proxy.unmarshall( xml, "com/softhog/security/loginmodule/service/proxy/eclipselink-oxm-signonuser-details.xml",
							"com.softhog.security.loginmodule.domain" );
			
			proxy.throwExceptionIfErrorCodeSet( cif.getErrorNode() );
			
			staff.setSn( cif.getSn() );
			
			staff.setCn( cif.getCn() );
			
			staff.setWorkforceId( cif.getCn() );
			
			staff.setRoles( cif.getNbpUserRoleList() );
		}
		catch( CommsException ex )
		{
			throw new ProxyException( "Signon failure getting ldap details " 
							+ staff + ex.getMessage(), ex );
		}
	}
}