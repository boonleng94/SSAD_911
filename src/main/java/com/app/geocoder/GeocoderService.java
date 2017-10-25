package com.app.geocoder;

import java.io.IOException;

import org.springframework.stereotype.Service;

import wicket.contrib.gmap.api.GLatLng;
@Service

public class GeocoderService {

	public Response convertToGeoCode(String Address) throws IOException
	{
		Geocoder geocoder= new Geocoder();
		return geocoder.geocode(Address);
	}
}
