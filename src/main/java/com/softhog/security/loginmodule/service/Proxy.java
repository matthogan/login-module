/**
 *
 */
package com.softhog.security.loginmodule.service;

import java.io.StringReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.lang.StringUtils;
import org.eclipse.persistence.jaxb.JAXBContextProperties;

import com.softhog.security.loginmodule.domain.ErrorNode;
import com.softhog.security.loginmodule.domain.ProxyException;

import com.softhog.dsl.proxy.ClientProxy;
import com.softhog.dsl.proxy.CommsException;

/**
 * A stateful utility for calling the bus and dsl proxies. Depends on service_patterns.xml State - map of xml contexts.
 * 
 * @author
 * 
 */
public class Proxy
{
    /** Arg types for proxy call */
    @SuppressWarnings ( "rawtypes")
    private static Class[] argTypes = { String.class, String.class, String.class, String.class, String.class,
                    String.class, String.class };

    private ConfigServiceImpl configService = new ConfigServiceImpl();

    /**
     * A bean for calling the bus and dsl proxies
     */
    public Proxy()
    {
    }

    public void throwExceptionIfErrorCodeSet( ErrorNode errorNode )
    {
        if( errorNode != null && StringUtils.isNotBlank( errorNode.getCode() ) && !errorNode.getCode().equals( "0" ) )
        {
            throw new ProxyException( errorNode );
        }
    }

    /**
     * Convert xml to an object. Not the fastest thing in the world. Pool unmarshallers.
     * 
     * @param xml
     *            - the data to jam into the object
     * @param oxmMapperFilename
     *            - path to object mapping
     * @param packageName
     *            - package containing the class
     * @return - the populated object
     * @throws ProxyException
     *             - all over the place
     */
    @SuppressWarnings ( "unchecked")
    public <U> U unmarshall( String xml, String oxmMapperFilename, String packageName )
    {
        try
        {
            JAXBContext jc = getContext( oxmMapperFilename, packageName );

            Unmarshaller u = jc.createUnmarshaller();

            return (U) u.unmarshal( new StringReader( xml ) );
        }
        catch( JAXBException e )
        {
            throw new ProxyException( "JAXBException occurred unmarshalling XML", e );
        }
        catch( ClassNotFoundException e )
        {
            throw new ProxyException( "JAXBException occurred unmarshalling XML", e );
        }
    }

    /**
     * Specialised for staff id;
     * 
     * @param serviceName
     * @param staffId
     * @return
     */
    @SuppressWarnings ( "unchecked")
    public <U extends ClientProxy> U getProxy( String serviceName, String staffId )
    {
        ClientProxy cp = null;

        try
        {
            Object[] args = { configService.getApplicationId(), "N", staffId, "5324654654",
                            configService.getDefaultNsc(), staffId, configService.getJurisdiction() };

            cp = getProxy( serviceName, args, argTypes );
        }
        catch( Exception ex )
        {
            throw new ProxyException( "Error occurred getting Proxy class for " + serviceName, ex );
        }

        return (U) cp;
    }

    /**
     * @return
     */
    public ErrorNode unmarshallErrorNode( String xml )
    {
        ErrorNode errorNode = unmarshall( xml, "com/softhog/security/loginmodule/domain/eclipselink-oxm-error_node.xml",
                        "com.softhog.security.loginmodule.domain" );

        return errorNode;
    }

    /**
     * Convert xml to an object. Not the fastest thing in the world. Pool unmarshallers.
     * 
     * @param xml
     *            - the data to jam into the object
     * @param packageToOxmMapper
     *            - map of key=(package containing the class):value=(path to object mapping)
     * @return - the populated object
     * @throws ProxyException
     *             - all over the place
     */
    public <U> U unmarshall( String xml, Map<String, String> packageToOxmMapper )
    {
        StringBuilder jaxbPackage = new StringBuilder();
        StringBuilder jaxbFile = new StringBuilder();

        for( Map.Entry<String, String> e : packageToOxmMapper.entrySet() )
        {
            jaxbFile.append( e.getValue() + ':' );
            jaxbPackage.append( e.getKey() + ':' );
        }

        return unmarshall( xml, jaxbFile.toString(), jaxbPackage.toString() );

    }

    /**
     * Calls the host and returns the response via a proxy.
     * 
     * @param cp
     *            - fully configured proxy
     * @return - xml response
     * @throws ProxyException
     */
    public String callService( ClientProxy cp )
    {
        try
        {
            return cp.doTask();
        }
        catch( CommsException ex )
        {
            throw new ProxyException( "Failed to call a bus or dsl service [" + cp + "] " + ex.getMessage(), ex );
        }
    }

    /**
     * Helper method to create an instance of a Markup proxy using reflection.
     * 
     * @param serviceName
     *            Name of the service proxy e.g. MarkupService001, value passed from Client
     * @return
     * @throws ProxyNotFoundException
     */
    @SuppressWarnings ( "unchecked")
    public <U extends ClientProxy> U getProxy( String serviceName )
    {
        ClientProxy cp = null;

        try
        {
            Object[] args = { configService.getApplicationId(), "N", configService.getMessageLayerStaffId(),
                            "5324654654", configService.getDefaultNsc(), configService.getMessageLayerStaffId(),
                            configService.getJurisdiction() };

            cp = getProxy( serviceName, args, argTypes );
        }
        catch( Exception ex )
        {
            throw new ProxyException( "Error occurred getting Proxy class for " + serviceName, ex );
        }

        return (U) cp;
    }

    /**
     * @param serviceName
     * @param args
     * @param classArgTypes
     * @return
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    @SuppressWarnings ( { "unchecked", "rawtypes" })
    private ClientProxy getProxy( String serviceName, Object[] args, Class[] classArgTypes )
                    throws ClassNotFoundException, NoSuchMethodException, InstantiationException,
                    IllegalAccessException, InvocationTargetException
    {
        Class serviceClass = Class.forName( serviceName );

        Constructor<?> cons = serviceClass.getConstructor( classArgTypes );

        ClientProxy cp = (ClientProxy) cons.newInstance( args );

        return cp;
    }

    /**
     * @param oxmMapperFilename
     * @param packageName
     * @return
     * @throws JAXBException
     * @throws ClassNotFoundException
     */
    public JAXBContext getContext( String oxmMapperFilename, String packageName ) throws JAXBException,
                    ClassNotFoundException
    {

        if( StringUtils.isBlank( oxmMapperFilename ) )
        {
            return JAXBContext.newInstance( Thread.currentThread().getContextClassLoader().loadClass( packageName ) );
        }

        Map<String, Source> metadata = new HashMap<String, Source>();
        Map<String, Object> properties = new HashMap<String, Object>();

        String[] oxmMapperFilenames = oxmMapperFilename.split( ":" );
        String[] packageNames = packageName.split( ":" );
        int len = packageNames.length;

        while( --len >= 0 )
        {
            metadata.put( packageNames[ len ],
                            new StreamSource( Proxy.class.getClassLoader().getResourceAsStream(
                                            oxmMapperFilenames[ len ] ) ) );
        }

        properties.put( JAXBContextProperties.OXM_METADATA_SOURCE, metadata );

        JAXBContext jc = JAXBContext.newInstance( packageName, Thread.currentThread().getContextClassLoader(),
                        properties );

        return jc;
    }
}
