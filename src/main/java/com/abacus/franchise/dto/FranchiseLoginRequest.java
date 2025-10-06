package com.abacus.franchise.dto;

import com.abacus.franchise.utility.FranchiseStatus;

public class FranchiseLoginRequest {

	private Long franchise_id;
	private String franchise_number;
	private String franchise_name;
	private String franchise_email;
	private FranchiseStatus franchiseStatus;

	public Long getFranchise_id() {
		return franchise_id;
	}

	public void setFranchise_id(Long franchise_id) {
		this.franchise_id = franchise_id;
	}

	public String getFranchise_number() {
		return franchise_number;
	}

	public void setFranchise_number(String franchise_number) {
		this.franchise_number = franchise_number;
	}

	public String getFranchise_name() {
		return franchise_name;
	}

	public void setFranchise_name(String franchise_name) {
		this.franchise_name = franchise_name;
	}

	public String getFranchise_email() {
		return franchise_email;
	}

	public void setFranchise_email(String franchise_email) {
		this.franchise_email = franchise_email;
	}

	public FranchiseStatus getFranchiseStatus() {
		return franchiseStatus;
	}

	public void setFranchiseStatus(FranchiseStatus franchiseStatus) {
		this.franchiseStatus = franchiseStatus;
	}

	public FranchiseLoginRequest(Long franchise_id, String franchise_number, String franchise_name,
			String franchise_email, FranchiseStatus franchiseStatus) {
		super();
		this.franchise_id = franchise_id;
		this.franchise_number = franchise_number;
		this.franchise_name = franchise_name;
		this.franchise_email = franchise_email;
		this.franchiseStatus = franchiseStatus;
	}

	public FranchiseLoginRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

}
