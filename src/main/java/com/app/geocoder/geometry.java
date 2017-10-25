package com.app.geocoder;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@AllArgsConstructor
@Embeddable
public class geometry {
	
	public geometry(){
		
	}
	
	public location getLocation() {
		return Location;
	}
	
	public void setLocation(location location) {
		Location = location;
	}
	
	@Embedded
	private location Location;
}
