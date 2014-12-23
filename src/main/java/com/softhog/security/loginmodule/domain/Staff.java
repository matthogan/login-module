/**
 * 
 */
package com.softhog.security.loginmodule.domain;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * Cust. Includes the api, the database, and cif fields.
 * 
 * @author
 * 
 */
public class Staff 
{
	/** */
	private String workforceId;

	/** */
	private String nsc;

	/** */
	private String sn;

    /** */
	private String password;

	/**
	 * Personal
	 */
	public Staff()
	{
	}

	/**
	 * @return the workforceId
	 */
	public String getWorkforceId()
	{
		return workforceId;
	}

	/**
	 * @param workforceId
	 *            the workforceId to set
	 */
	public void setWorkforceId( String workforceId )
	{
		this.workforceId = workforceId;
	}

	/**
	 * @return the nsc
	 */
	public String getNsc()
	{
		return nsc;
	}

	/**
	 * @param nsc
	 *            the nsc to set
	 */
	public void setNsc( String nsc )
	{
		this.nsc = nsc;
	}

	/**
	 * @return the sn
	 */
	public String getSn()
	{
		return sn;
	}

	/**
	 * @param sn
	 *            the sn to set
	 */
	public void setSn( String sn )
	{
		this.sn = sn;
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
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword( String password )
	{
		this.password = password;
	}
}
