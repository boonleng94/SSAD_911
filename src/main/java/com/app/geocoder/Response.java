package com.app.geocoder;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
public class Response  implements Serializable{

	@Id
    @GeneratedValue( strategy = GenerationType.AUTO )
	@Embedded
	private results[] results;
	private String Status;
	
	public Response(){
		
	}

	public results[] getResults() {
		return results;
	}

	public void setResults(results results[]) {
		this.results = results;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}
}
