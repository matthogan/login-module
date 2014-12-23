/**
 * 
 */
package com.softhog.security.loginmodule;

import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softhog.security.loginmodule.domain.LdapStaff;
import com.softhog.security.loginmodule.service.ConfigServiceImpl;
import com.softhog.security.loginmodule.service.SignonServiceImpl;

/**
 * 
 * 
 */
public class SignonUserLoginModule implements LoginModule
{
    /** */
    private Logger logger = LoggerFactory.getLogger( SignonUserLoginModule.class );
    
    /** */
    private ConfigServiceImpl configService = new ConfigServiceImpl();

    /** Callback handler to store between initialization and authentication. */
    private CallbackHandler handler;

    /** Subject to store. */
    private Subject subject;

    /** Login name. */
    private String login;

    /**
     * This implementation always return false.
     * 
     * @see javax.security.auth.spi.LoginModule#abort()
     */
    public boolean abort() throws LoginException
    {
        return false;
    }

    /**
     * This is where, should the entire authentication process succeeds, principal would be set.
     * 
     * @see javax.security.auth.spi.LoginModule#commit()
     */
    public boolean commit() throws LoginException
    {
        try
        {
            UserPrincipal user = new UserPrincipal( login );
            RolePrincipal role = new RolePrincipal( "Admin" );

            subject.getPrincipals().add( user );
            subject.getPrincipals().add( role );

            return true;
        }
        catch( Exception e )
        {
            throw new LoginException( e.getMessage() );
        }
    }

    /**
     * This implementation ignores both state and options.
     * 
     * @see javax.security.auth.spi.LoginModule#initialize(javax.security.auth.Subject,
     *      javax.security.auth.callback.CallbackHandler, java.util.Map, java.util.Map)
     */
    @SuppressWarnings ( "rawtypes")
    public void initialize( Subject aSubject, CallbackHandler aCallbackHandler, Map aSharedState, Map aOptions )
    {
        handler = aCallbackHandler;
        subject = aSubject;
    }

    /**
     * Sign on using the internal sign on service
     * 
     * @see javax.security.auth.spi.LoginModule#login()
     */
    public boolean login() throws LoginException
    {

        Callback[] callbacks = new Callback[ 2 ];
        callbacks[ 0 ] = new NameCallback( "login" );
        callbacks[ 1 ] = new PasswordCallback( "password", true );

        try
        {
            handler.handle( callbacks );

            String staffId = ((NameCallback) callbacks[ 0 ]).getName();
            String password = String.valueOf( ((PasswordCallback) callbacks[ 1 ]).getPassword() );
            
            LdapStaff staff = new LdapStaff();
            
            staff.setWorkforceId( staffId );
            staff.setStaffId( staffId );
            staff.setPassword( new String( password ) );
            
            // this should be set by incoming message...?
            staff.setLdapRole( configService.getPropertyValue( "ldap.role.admin" ) );
            
            SignonServiceImpl signonService = new SignonServiceImpl();

            signonService.signon( staff );
            
            login = staff.getSn();

            return true;
        }
        catch( Exception ex )
        {
            logger.error( ex.getMessage(), ex );
            
            throw new LoginException( ex.getMessage() );
        }
    }

    /**
     * Clears subject from principal and credentials.
     * 
     * @see javax.security.auth.spi.LoginModule#logout()
     */
    public boolean logout() throws LoginException
    {
        try
        {
            UserPrincipal user = new UserPrincipal( login );
            RolePrincipal role = new RolePrincipal( "Admin" );

            subject.getPrincipals().remove( user );
            subject.getPrincipals().remove( role );

            return true;
        }
        catch( Exception e )
        {
            throw new LoginException( e.getMessage() );
        }
    }
}
