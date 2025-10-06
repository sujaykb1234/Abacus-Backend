package com.abacus.franchise.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Taluka {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long taluka_id;
	private String taluka_name;
	private Long district_id;

	public Long getTaluka_id() {
		return taluka_id;
	}

	public void setTaluka_id(Long taluka_id) {
		this.taluka_id = taluka_id;
	}

	public String getTaluka_name() {
		return taluka_name;
	}

	public void setTaluka_name(String taluka_name) {
		this.taluka_name = taluka_name;
	}

	public Long getDistrict_id() {
		return district_id;
	}

	public void setDistrict_id(Long district_id) {
		this.district_id = district_id;
	}

	public Taluka(Long taluka_id, String taluka_name, Long district_id) {
		super();
		this.taluka_id = taluka_id;
		this.taluka_name = taluka_name;
		this.district_id = district_id;
	}

	public Taluka() {
		super();
		// TODO Auto-generated constructor stub
	}

}
