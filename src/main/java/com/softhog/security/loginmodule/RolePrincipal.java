/**
 * 
 */
package com.softhog.security.loginmodule;

import java.io.Serializable;
import java.security.Principal;

/**
 * @author matto
 * 
 */
public class RolePrincipal implements Principal, Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 6366209489336020927L;
    /** */
    private String name;

    /**
     * 
     */
    public RolePrincipal()
    {
    }

    /**
     * 
     */
    public RolePrincipal( String name )
    {
        this.name = name;
    }

    /**
     * 
     * @see java.security.Principal#getName()
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName( String name )
    {
        this.name = name;
    }

}
