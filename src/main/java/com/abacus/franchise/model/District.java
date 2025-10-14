package com.abacus.franchise.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class District {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long district_id;
	private String district_name;
	@ManyToOne
	private State state;
	
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public Long getDistrict_id() {
		return district_id;
	}
	public void setDistrict_id(Long district_id) {
		this.district_id = district_id;
	}
	public String getDistrict_name() {
		return district_name;
	}
	public void setDistrict_name(String district_name) {
		this.district_name = district_name;
	}
	
}
