/**
 * 
 */
package com.softhog.security.loginmodule.domain;

import java.util.List;

/**
 * Common definition for back end domain objects, typically
 * used to data transfer with database or message layers.
 * 
 * @author
 * 
 */
public class DomainModel<T>
{
	/** Bodge for the oracle cursor variable pattern */
	private List<T> resultSetList = null;

	/** */
	private Integer applicationId = null;

	/** This should be mandatory */
	protected String staffId = null;
	
    /** */
    protected ErrorNode errorNode;

	/**
     * 
     */
	public DomainModel()
	{
	}

	/**
	 * @return the staffId
	 */
	public String getStaffId()
	{
		return staffId;
	}

	/**
	 * @param staffId
	 *            the staffId to set
	 */
	public void setStaffId( String staffId )
	{
		this.staffId = staffId;
	}

	/**
	 * @return the resultSetList
	 */
	public List<T> getResultSetList()
	{
		return resultSetList;
	}

	/**
	 * @param resultSetList
	 *            the resultSetList to set
	 */
	public void setResultSetList( List<T> resultSetList )
	{
		this.resultSetList = resultSetList;
	}

	/**
	 * @return the applicationId
	 */
	public Integer getApplicationId()
	{
		return applicationId;
	}

	/**
	 * @param applicationId
	 *            the applicationId to set
	 */
	public void setApplicationId( Integer applicationId )
	{
		this.applicationId = applicationId;
	}
}
