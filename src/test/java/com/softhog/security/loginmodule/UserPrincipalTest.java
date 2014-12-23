/**
 * 
 */
package com.softhog.security.loginmodule;

import static org.junit.Assert.*;

import org.junit.Test;

import com.softhog.security.loginmodule.UserPrincipal;

/**
 * @author 
 * 
 */
public class UserPrincipalTest
{

    public UserPrincipalTest()
    {
    }

    @Test
    public void name()
    {
        String name = "x";

        UserPrincipal p = new UserPrincipal( name );

        assertEquals( name, p.getName() );

        p.setName( name );

        assertEquals( name, p.getName() );

        p = new UserPrincipal();

        assertNull( p.getName() );
    }

}
