package com.app.geocoder;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.StringTokenizer;
import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.DeserializationConfig;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import wicket.contrib.gmap.api.GLatLng;
import wicket.contrib.gmap.util.GeocoderException;


/**
 * Geocoder. See: http://www.google.com/apis/maps/documentation/services.html# Geocoding_Direct
 *
 * @author Thijs Vonk
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Geocoder implements Serializable
{

    private static final long serialVersionUID = 1L;
    // Constants
    public static final String OUTPUT_CSV = "csv";
    public static final String OUTPUT_XML = "xml";
    public static final String OUTPUT_KML = "kml";
    public static final String OUTPUT_JSON = "json";
    private final String output = OUTPUT_JSON;

    public Geocoder()
    {
    }

    public Response decode(String response) throws JsonParseException, JsonMappingException, IOException
    {
    	
    	ObjectMapper mapper = new ObjectMapper();
    	mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    	 return (Response)mapper.readValue(response,Response.class);
    }

    /**
     * builds the google geo-coding url
     *
     * @param address
     * @return
     */
    public String encode(final String address)
    {
        return "http://maps.googleapis.com/maps/api/geocode/json?address=" + urlEncode(address) + "&sensor=false";
    }

    /**
     * @param address
     * @return
     * @throws IOException
     */
    public Response geocode(final String address) throws IOException
    {
        InputStream is = invokeService(encode(address));
        if (is != null)
        {
            try
            {
                String content = IOUtils.toString(is,StandardCharsets.UTF_8);
                return decode(content);
            }
            finally
            {
                is.close();
            }
        }
        return null;
    }

    /**
     * fetches the url content
     *
     * @param address
     * @return
     * @throws IOException
     */
    protected InputStream invokeService(final String address) throws IOException
    {
        URL url = new URL(address);
        return url.openStream();
    }

    /**
     * url-encode a value
     *
     * @param value
     * @return
     */
    private String urlEncode(final String value)
    {
        try
        {
            return URLEncoder.encode(value, "UTF-8");
        }
        catch (UnsupportedEncodingException ex)
        {
            throw new RuntimeException(ex.getMessage());
        }
    }
}
