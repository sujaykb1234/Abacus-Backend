package com.abacus.franchise.dto;

import com.abacus.franchise.utility.FranchiseStatus;

public class FranchiseUserPassResponse {

	private String franchise_number;
	private String franchise_name;
	private String userName; 
	private String franchise_password;
	private FranchiseStatus franchiseStatus;

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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFranchise_password() {
		return franchise_password;
	}

	public void setFranchise_password(String franchise_password) {
		this.franchise_password = franchise_password;
	}

	public FranchiseStatus getFranchiseStatus() {
		return franchiseStatus;
	}

	public void setFranchiseStatus(FranchiseStatus franchiseStatus) {
		this.franchiseStatus = franchiseStatus;
	}

	public FranchiseUserPassResponse(String franchise_number, String franchise_name, String userName,
			String franchise_password, FranchiseStatus franchiseStatus) {
		super();
		this.franchise_number = franchise_number;
		this.franchise_name = franchise_name;
		this.userName = userName;
		this.franchise_password = franchise_password;
		this.franchiseStatus = franchiseStatus;
	}

	public FranchiseUserPassResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

}
