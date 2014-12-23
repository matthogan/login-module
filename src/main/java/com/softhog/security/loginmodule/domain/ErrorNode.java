/**
 * 
 */
package com.softhog.security.loginmodule.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;


/**
 * Error node as per well-known error element returned from bus/dsl messages.
 * 
 * @author
 * 
 */
@XmlRootElement
public class ErrorNode implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = -3160058261381822436L;

    /** */
    private String code = null;

    /** */
    private String msg = null;

    /** */
    private String sev = null;

    /** */
    private String source = null;
    
    private String keyArea = null;

    /**
     * 
     */
    public ErrorNode()
    {
    }

    /**
     * @return the code
     */
    public String getCode()
    {
        return code;
    }

    /**
     * @param code
     *            the code to set
     */
    public void setCode( String code )
    {
        this.code = code;
    }

    /**
     * @return the msg
     */
    public String getMsg()
    {
        return msg;
    }

    /**
     * @param msg
     *            the msg to set
     */
    public void setMsg( String msg )
    {
        this.msg = msg;
    }

    /**
     * @return the sev
     */
    public String getSev()
    {
        return sev;
    }

    /**
     * @param sev
     *            the sev to set
     */
    public void setSev( String sev )
    {
        this.sev = sev;
    }

    /**
     * @return the source
     */
    public String getSource()
    {
        return source;
    }

    /**
     * @param source
     *            the source to set
     */
    public void setSource( String source )
    {
        this.source = source;
    }

    @Override
    public boolean equals(Object object){
    	if (object != null && object instanceof ErrorNode){
    		ErrorNode o2 = (ErrorNode) object;
    		return (StringUtils.equals(this.getCode(), o2.getCode()));
    	}
    	return false;
    }
    
    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return ReflectionToStringBuilder.toString( this );
    }

	public String getKeyArea() {
		return keyArea;
	}

	public void setKeyArea(String keyArea) {
		this.keyArea = keyArea;
	}
}
