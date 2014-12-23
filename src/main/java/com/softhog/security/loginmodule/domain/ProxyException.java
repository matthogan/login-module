/**
 * 
 */
package com.softhog.security.loginmodule.domain;

import java.io.Serializable;


/**
 * <p>
 * To propogate a proxy exception
 * 
 * @author
 * 
 */
public class ProxyException extends RuntimeException implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = -3497682499598434810L;

    /** Integrated into the generic error string */
    private ErrorNode errorNode;
    
    /**
     * No response
     */
    public static final String NULL_PROXY_XML_RESP = "NULL_PROXY_XML_RESP";

    /**
     * @param code - error code identifying the error message
     * @param messages - the text items to be processed into the string identified
     * by the code
     */
    public ProxyException( ErrorNode errorNode )
    {
        this.errorNode = errorNode;
    }

    /**
     * @param message - usual error message
     */
    public ProxyException( String message )
    {
        super( message );
    }

    /**
     * @param message - usual error message
     * @param cause - usual cause
     */
    public ProxyException( String message, Throwable cause )
    {
        super( message, cause );
    }
    
    /**
     * Returns the populated error message based on the error string
     * 
     * @todo - decide on error format
     * @see java.lang.Throwable#getMessage()
     */
    public String getMessage()
    {
    	String m = "";
    	
        if( errorNode != null )
        {
            m += errorNode.toString();
        }
    	
    	if( super.getMessage() != null )
    	{
    		m += super.getMessage();
    	}
        
        return m;
    }

    /**
     * @return the code
     */
    public ErrorNode getErrorNode()
    {
        return errorNode;
    }

    /**
     * @param code
     *            the code to set
     */
    public void setErrorNode( ErrorNode errorNode )
    {
        this.errorNode = errorNode;
    }
}
