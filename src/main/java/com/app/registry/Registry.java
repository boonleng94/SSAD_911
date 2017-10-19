package com.app.registry;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "registry", schema = "911")
public class Registry {

	@Id
	private String nric;
	private String name;
	private String address;
	private String dob;

	public Registry() {
		
	}

	public String getNric() {
		return nric;
	}

	public void setNric(String nric) {
		this.nric = nric;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	@Override
	public String toString() {
		return "Registry [nric=" + nric + ", name=" + name + ", address=" + address + ", dob=" + dob + "]";
	}
}