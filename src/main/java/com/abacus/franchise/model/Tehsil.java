package com.abacus.franchise.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Tehsil {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long tehsil_id;
	private String tehsil_name;
	@ManyToOne
	private District district;
	
	public District getDistrict() {
		return district;
	}
	public void setDistrict(District district) {
		this.district = district;
	}
	public Long getTehsil_id() {
		return tehsil_id;
	}
	public void setTehsil_id(Long tehsil_id) {
		this.tehsil_id = tehsil_id;
	}
	public String getTehsil_name() {
		return tehsil_name;
	}
	public void setTehsil_name(String tehsil_name) {
		this.tehsil_name = tehsil_name;
	}
	
}
