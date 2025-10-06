package com.abacus.franchise.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class AddressDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long address_id;

	private String line1;

	private String line2;

	private String city;

	private String state;

	private String pincode;

	private String country;

	private Double longitude;

	private Double latitude;

	private String created_time;

	private String modified_time;

	private boolean status = true;

	public Long getAddress_id() {
		return address_id;
	}

	public void setAddress_id(Long address_id) {
		this.address_id = address_id;
	}

	public String getLine1() {
		return line1;
	}

	public void setLine1(String line1) {
		this.line1 = line1;
	}

	public String getLine2() {
		return line2;
	}

	public void setLine2(String line2) {
		this.line2 = line2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public String getCreated_time() {
		return created_time;
	}

	public void setCreated_time(String created_time) {
		this.created_time = created_time;
	}

	public String getModified_time() {
		return modified_time;
	}

	public void setModified_time(String modified_time) {
		this.modified_time = modified_time;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public AddressDTO(Long address_id, String line1, String line2, String city, String state, String pincode,
			String country, Double longitude, Double latitude, String created_time, String modified_time,
			boolean status) {
		super();
		this.address_id = address_id;
		this.line1 = line1;
		this.line2 = line2;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
		this.country = country;
		this.longitude = longitude;
		this.latitude = latitude;
		this.created_time = created_time;
		this.modified_time = modified_time;
		this.status = status;
	}

	public AddressDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

}
