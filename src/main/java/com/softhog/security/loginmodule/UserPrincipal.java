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
public class UserPrincipal implements Principal, Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 1569711483842155050L;
    /** */
    private String name;

    /**
     * 
     */
    public UserPrincipal()
    {
    }

    /**
     * 
     */
    public UserPrincipal( String name )
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
