/**
 * 
 */
package com.softhog.security.loginmodule.domain;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * Proxy field response
 * <p>
 * <code>
 *    <Field name="A0CBAIPPC_CUST_NADDR_LINE3">
 *        <Value>CREDIT SCORE</Value>
 *        <Change>U</Change>
 *    </Field>
 * </code>
 * @author
 *
 */
public class Field
{
    /** */
    private String name = null;
    /** */
    private String value = null;
    /** */
    private String change = null;

    /**
     * 
     */
    public Field()
    {
    }

    /**
     * @return the name
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

    /**
     * @return the value
     */
    public String getValue()
    {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue( String value )
    {
        this.value = value;
    }

    /**
     * @return the change
     */
    public String getChange()
    {
        return change;
    }

    /**
     * @param change the change to set
     */
    public void setChange( String change )
    {
        this.change = change;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return ReflectionToStringBuilder.toString( this );
    }

}
