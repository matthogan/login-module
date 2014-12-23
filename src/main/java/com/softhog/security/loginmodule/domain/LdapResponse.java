/**
 * 
 */
package com.softhog.security.loginmodule.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;



/**
 * Some common fields for cif update responses.
 * 
 * Should the proxy response be stored in mem as a 
 * dom or use the xpath, or sax, stax, etc...?
 * 
 * @author
 *
 */
public class LdapResponse
{
    /** */
    private String sn;
    /** */
    private String cn;
    /** */
    private String nsc;
    /** */
    private String fullName;
    /** */
    private ErrorNode errorNode;
    /** */
    private List<String> nbpUserRoleLists = new ArrayList<String>();

    /**
     * 
     */
    public LdapResponse()
    {
    }

    /**
     * @return the errorNode
     */
    public ErrorNode getErrorNode()
    {
        return errorNode;
    }

    /**
     * @param errorNode the errorNode to set
     */
    public void setErrorNode( ErrorNode errorNode )
    {
        this.errorNode = errorNode;
    }

    /**
     * @return the field
     */
    public Field getField()
    {
        return null;
    }

    /**
     * @param field the field to set
     */
    public void setNbpUserRoleList( List<String> nbpUserRoleList )
    {        
    	nbpUserRoleLists.addAll( nbpUserRoleList );
    }
    
    /**
     * Iterate over the fields
     * 
     * @return
     */
    public Iterator<String> nbpUserRoleLists()
    {
        return nbpUserRoleLists.iterator();
    }
    
    /**
     * @return
     */
    public List<String> getNbpUserRoleList()
    {
    	return nbpUserRoleLists;
    }

    /**
     * @return the nsc
     */
    public String getNsc()
    {
        return nsc;
    }

    /**
     * @param nsc the nsc to set
     */
    public void setNsc( String nsc )
    {
        this.nsc = nsc;
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
	 * @return the sn
	 */
	public String getSn()
	{
		return sn;
	}

	/**
	 * @param sn the sn to set
	 */
	public void setSn( String sn )
	{
		this.sn = sn;
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
	 * @return the fullName
	 */
	public String getFullName()
	{
		return fullName;
	}

	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName( String fullName )
	{
		this.fullName = fullName;
	}
}
