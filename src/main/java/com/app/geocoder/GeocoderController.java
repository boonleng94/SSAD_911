package com.app.geocoder;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import wicket.contrib.gmap.api.GLatLng;

@RestController 
public class GeocoderController {
	@Autowired
	private GeocoderService geocoderService;
	
	@RequestMapping("/convert/{address}")
	public Response getGeoCode(@PathVariable String address) throws IOException
	{
		return geocoderService.convertToGeoCode(address);
	}
	@RequestMapping("/convert")
	public Response getGeoCode2() throws IOException
	{
		return geocoderService.convertToGeoCode("Choa Chu Kang Ave 2");
	}
}
