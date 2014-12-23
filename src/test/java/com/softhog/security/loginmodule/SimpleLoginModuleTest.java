/**
 * 
 */
package com.softhog.security.loginmodule;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.login.LoginException;

import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.softhog.security.loginmodule.SimpleLoginModule;

/**
 * @author
 * 
 */
public class SimpleLoginModuleTest
{

    public SimpleLoginModuleTest()
    {
    }

    @Test
    public void initialize()
    {
        SimpleLoginModule lm = new SimpleLoginModule();

        CallbackHandler c = Mockito.mock( CallbackHandler.class );

        Subject s = new Subject();

        lm.initialize( s, c, null, null );
    }

    @Test
    public void login()
    {
        final String name = "x";
        final char[] password = "x".toCharArray();

        SimpleLoginModule lm = new SimpleLoginModule();

        CallbackHandler c = Mockito.mock( CallbackHandler.class );

        Subject s = new Subject();

        mockHandle( c, name, password );

        lm.initialize( s, c, null, null );

        try
        {
            assertEquals( true, lm.login() );
        }
        catch( LoginException ex )
        {
            ex.printStackTrace();

            fail( ex.getMessage() );
        }
    }

    @Test
    public void loginEx()
    {
        final String name = "x";
        final char[] password = "y".toCharArray();

        SimpleLoginModule lm = new SimpleLoginModule();

        CallbackHandler c = Mockito.mock( CallbackHandler.class );

        Subject s = new Subject();

        mockHandle( c, name, password );

        lm.initialize( s, c, null, null );

        try
        {
            lm.login();
            
            fail( "no exception thrown when expected" );
        }
        catch( LoginException ex )
        {  
        }
    }

    private void mockHandle( CallbackHandler c, String name, char[] password )
    {
        final NameCallback nameCallback = new NameCallback( "login" );
        nameCallback.setName( name );

        final PasswordCallback passwordCallback = new PasswordCallback( "password", true );
        passwordCallback.setPassword( password );

        try
        {
            Mockito.doAnswer( new Answer<Void>()
            {
                public Void answer( InvocationOnMock invocation )
                {
                    Callback[] callbacks = (Callback[]) invocation.getArguments()[ 0 ];

                    callbacks[ 0 ] = nameCallback;
                    callbacks[ 1 ] = passwordCallback;

                    return null;
                }
            } ).when( c ).handle( Mockito.any( Callback[].class ) );
        }
        catch( Exception ex )
        {
            ex.printStackTrace();

            fail( ex.getMessage() );
        }
    }

    @Test
    public void commit()
    {
        final String name = "x";
        final char[] password = "x".toCharArray();

        final NameCallback nameCallback = new NameCallback( "login" );
        nameCallback.setName( name );

        final PasswordCallback passwordCallback = new PasswordCallback( "password", true );
        passwordCallback.setPassword( password );

        SimpleLoginModule lm = new SimpleLoginModule();

        CallbackHandler c = Mockito.mock( CallbackHandler.class );

        Subject s = new Subject();

        mockHandle( c, name, password );

        lm.initialize( s, c, null, null );

        try
        {
            assertEquals( true, lm.login() );
        }
        catch( LoginException ex )
        {
            ex.printStackTrace();

            fail( ex.getMessage() );
        }

        try
        {
            assertEquals( true, lm.commit() );
        }
        catch( LoginException ex )
        {
            ex.printStackTrace();

            fail( ex.getMessage() );
        }
    }

    @Test
    public void commitWithException()
    {
        final String name = "x";
        final char[] password = "x".toCharArray();

        final NameCallback nameCallback = new NameCallback( "login" );
        nameCallback.setName( name );

        final PasswordCallback passwordCallback = new PasswordCallback( "password", true );
        passwordCallback.setPassword( password );

        SimpleLoginModule lm = new SimpleLoginModule();

        CallbackHandler c = Mockito.mock( CallbackHandler.class );

        Subject s = new Subject();

        mockHandle( c, name, password );

        lm.initialize( s, c, null, null );

        try
        {
            assertEquals( true, lm.login() );
        }
        catch( LoginException ex )
        {
            ex.printStackTrace();

            fail( ex.getMessage() );
        }

        try
        {
            assertEquals( true, lm.commit() );
        }
        catch( LoginException ex )
        {
            ex.printStackTrace();

            fail( ex.getMessage() );
        }
    }

    @Test
    public void logout()
    {
        final String name = "x";
        final char[] password = "x".toCharArray();

        final NameCallback nameCallback = new NameCallback( "login" );
        nameCallback.setName( name );

        final PasswordCallback passwordCallback = new PasswordCallback( "password", true );
        passwordCallback.setPassword( password );

        SimpleLoginModule lm = new SimpleLoginModule();

        CallbackHandler c = Mockito.mock( CallbackHandler.class );

        Subject s = new Subject();

        mockHandle( c, name, password );

        lm.initialize( s, c, null, null );

        try
        {
            assertEquals( true, lm.login() );
        }
        catch( LoginException ex )
        {
            ex.printStackTrace();

            fail( ex.getMessage() );
        }

        try
        {
            assertEquals( true, lm.commit() );
        }
        catch( LoginException ex )
        {
            ex.printStackTrace();

            fail( ex.getMessage() );
        }

        try
        {
            assertEquals( true, lm.logout() );
        }
        catch( LoginException ex )
        {
            ex.printStackTrace();

            fail( ex.getMessage() );
        }
    }

    @Test
    public void abort()
    {
        SimpleLoginModule lm = new SimpleLoginModule();

        try
        {
            lm.abort();
        }
        catch( LoginException ex )
        {
            ex.printStackTrace();

            fail( ex.getMessage() );
        }
    }

}
