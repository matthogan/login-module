/**
 * 
 */
package com.softhog.security.loginmodule.domain;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * Cust. Includes the api, the database, and cif fields.
 * 
 * @author
 * 
 */
public class LdapStaff extends DomainModel<LdapStaff>
{
	/** */
	private List<String> roles = new ArrayList<String>();
	/** */
	private Staff staff = new Staff();
	/** */
	private String cn;
    /** Role the staff member is logging in as, verified against roles list from directory server */
    private String ldapRole;

	/**
	 * 
	 */
	public LdapStaff()
	{
	}
	
	/**
	 * @param staff
	 */
	public LdapStaff( Staff staff )
	{
		this.staff = staff;
	}

	/**
	 * @return the workforceId
	 */
	public String getWorkforceId()
	{
		return staff.getWorkforceId();
	}

	/**
	 * @param workforceId
	 *            the workforceId to set
	 */
	public void setWorkforceId( String workforceId )
	{
		staff.setWorkforceId( workforceId );
	}

	/**
	 * @return the nsc
	 */
	public String getNsc()
	{
		return staff.getNsc();
	}

	/**
	 * @param nsc
	 *            the nsc to set
	 */
	public void setNsc( String nsc )
	{
		staff.setNsc( nsc );
	}

	/**
	 * @return the sn
	 */
	public String getSn()
	{
		return staff.getSn();
	}

	/**
	 * @param sn
	 *            the sn to set
	 */
	public void setSn( String sn )
	{
		staff.setSn( sn );
	}

	/**
	 * @return the role
	 */
	public List<String> getRole()
	{
		return roles;
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole( String role )
	{
		this.roles.add( role );
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRoles( List<String> roles )
	{
		this.roles = roles;
	}

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return ReflectionToStringBuilder.toString( this );     
    }

	/**
	 * @return the password
	 */
	public String getPassword()
	{
		return staff.getPassword();
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword( String password )
	{
		staff.setPassword( password );
	}

	/**
	 * @return the cn
	 */
	public String getCn()
	{
		return cn;
	}

	/**
	 * @param cn the cn to set
	 */
	public void setCn( String cn )
	{
		this.cn = cn;
	}

    /**
     * @return the ldapRole
     */
    public String getLdapRole()
    {
        return ldapRole;
    }

    /**
     * @param ldapRole the ldapRole to set
     */
    public void setLdapRole( String ldapRole )
    {
        this.ldapRole = ldapRole;
    }
}
