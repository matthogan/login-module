/**
 * 
 */
package com.softhog.security.loginmodule.service;

import java.net.URL;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softhog.security.loginmodule.domain.KVException;

/**
 * @author
 * 
 */
public class ConfigServiceImpl
{
    /** */
    private Logger logger = LoggerFactory.getLogger( ConfigServiceImpl.class );

    /**
     * Data layer interface
     */
    private static Properties properties = new Properties();

    /** Ugly pre-load */
    static
    {
        try
        {
            URL u = Thread.currentThread().getContextClassLoader().getResource( "loginmodule.properties" );

            properties.load( u.openStream() );
        }
        catch( Exception ex )
        {
            ex.printStackTrace();

            throw new KVException( "PROPS_NOT_FOUND", ex );
        }
    }

    /**
     * 
     */
    public ConfigServiceImpl()
    {
    }

    /*
     * @see com.softhog.services.impl.AbstractConfigService#getPropertyValue(java.lang.String)
     */
    public String getPropertyValue( String key )
    {
        logger.debug( "Reading from config table " + key );

        return properties.getProperty( key );
    }

    public String getApplicationId()
    {
        return getPropertyValue( "app.id" );
    }

    /**
     * @see com.softhog.services.ConfigService#propertyEquals(java.lang.String, java.lang.String)
     */

    public Boolean propertyEquals( String key, String value )
    {
        return StringUtils.equals( value, getPropertyValue( key ) );
    }

    public String getJurisdiction()
    {
        return getPropertyValue( "jurisdiction" );
    }

    public String getDslAppUrl()
    {
        return getPropertyValue( "dsl.app.url" );
    }

    public String getBusAppUrl()
    {
        return getPropertyValue( "bus.app.url" );
    }

    public String getDistributedBusAppUrl()
    {
        return getPropertyValue( "distr.bus.app.url" );
    }

    public String getDefaultNsc()
    {
        return getPropertyValue( "default.nsc" );
    }

    public String getDefaultStaffId()
    {
        return getPropertyValue( "default.staff.id" );
    }

    /**
     * message.layer.staff.id
     * 
     * @see com.softhog.services.ConfigService#getMessageLayerStaffId()
     */

    public String getMessageLayerStaffId()
    {
        return getPropertyValue( "message.layer.staff.id" );
    }

    /**
     * @see com.softhog.services.ConfigService#isProduction()
     */

    public Boolean isProduction()
    {
        return StringUtils.equals( "prd", StringUtils.trim( getPropertyValue( "runtime.context" ) ) );
    }
}
