/**
 * 
 */
package com.softhog.security.loginmodule.domain;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * Some common fields for cif update responses.
 * 
 * Should the proxy response be stored in mem as a dom or use the xpath, or sax, stax, etc...?
 * 
 * @author
 * 
 */
public class CifResponse
{
	/** */
	private String updateKey = null;

	/** */
	private String cifKey = null;

	/** */
	private String nsc = null;

	/** */
	private String acctNo = null;

	/** */
	private ErrorNode errorNode = null;

	/** */
	// private List<Field> commonFields = new ArrayList<Field>();
	/** */
	private Map<String, Field> commonFieldMap = new HashMap<String, Field>();

	/** */
	// private List<Field> fields = new ArrayList<Field>();
	/** */
	private Map<String, Field> fieldMap = new HashMap<String, Field>();

	/**
     * 
     */
	public CifResponse()
	{
	}

	/**
	 * @return the updateKey
	 */
	public String getUpdateKey()
	{
		return updateKey;
	}

	/**
	 * @param updateKey
	 *            the updateKey to set
	 */
	public void setUpdateKey( String updateKey )
	{
		this.updateKey = updateKey;
	}

	/**
	 * @return the cifKey
	 */
	public String getCifKey()
	{
		return cifKey;
	}

	/**
	 * @param cifKey
	 *            the cifKey to set
	 */
	public void setCifKey( String cifKey )
	{
		this.cifKey = cifKey;
	}

	/**
	 * @return the errorNode
	 */
	public ErrorNode getErrorNode()
	{
		return errorNode;
	}

	/**
	 * @param errorNode
	 *            the errorNode to set
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
	 * @param field
	 *            the field to set
	 */
	public void setField( Field field )
	{
		// fields.add( field );

		fieldMap.put( field.getName(), field );
	}

	/**
	 * @return the field
	 */
	public Field getCommonField()
	{
		return null;
	}

	/**
	 * @param field
	 *            the field to set
	 */
	public void setCommonField( Field field )
	{
		commonFieldMap.put( field.getName(), field );
	}

	/**
	 * Iterate over the common fields
	 * 
	 * @return
	 */
	public Iterator<Field> commonFields()
	{
		return commonFieldMap.values().iterator();
	}

	/**
	 * Iterate over the fields
	 * 
	 * @return
	 */
	public Iterator<Field> fields()
	{
		return fieldMap.values().iterator();
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
	 * @return the acctNo
	 */
	public String getAcctNo()
	{
		return acctNo;
	}

	/**
	 * @param acctNo
	 *            the acctNo to set
	 */
	public void setAcctNo( String acctNo )
	{
		this.acctNo = acctNo;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return ReflectionToStringBuilder.toString( this );
	}

	public Map<String, Field> getFieldMap()
	{
		return fieldMap;
	}

	public String getFieldValue( String key )
	{
		if( fieldMap.containsKey( key ) )
		{
			Field f = fieldMap.get( key );

			return (f != null) ? StringUtils.trimToEmpty( f.getValue() ) : null;
		}
		return null;
	}

}
