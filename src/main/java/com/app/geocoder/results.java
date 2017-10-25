package com.app.geocoder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

import javax.persistence.*;
@Data
@AllArgsConstructor
@Embeddable 

public class results implements Serializable{

	@Embedded
	private geometry geometry;
	private String formatted_address;

	public geometry getGeometry() {
		return geometry;
	}

	public void setGeometry(geometry geo) {
		this.geometry = geo;
	}

	public String getFormatted_address() {
		return formatted_address;
	}

	public void setFormatted_address(String formatted_address) {
		this.formatted_address = formatted_address;
	}

	public results(){
		
	}
}
