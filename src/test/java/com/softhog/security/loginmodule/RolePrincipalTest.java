/**
 * 
 */
package com.softhog.security.loginmodule;

import static org.junit.Assert.*;

import org.junit.Test;

import com.softhog.security.loginmodule.RolePrincipal;

/**
 * @author 
 * 
 */
public class RolePrincipalTest
{

    public RolePrincipalTest()
    {
    }

    @Test
    public void name()
    {
        String name = "x";

        RolePrincipal p = new RolePrincipal( name );

        assertEquals( name, p.getName() );

        p.setName( name );

        assertEquals( name, p.getName() );

        p = new RolePrincipal();

        assertNull( p.getName() );
    }

}
