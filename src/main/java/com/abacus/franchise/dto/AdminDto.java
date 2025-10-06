package com.abacus.franchise.dto;

public class AdminDto {

	private Long admin_id;
	private String admin_username;
	private String admin_password;
	private String admin_mobileNo;
	private String admin_emailId;
	private String creation_time;
	private String modification_time;

	public Long getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(Long admin_id) {
		this.admin_id = admin_id;
	}

	public String getAdmin_username() {
		return admin_username;
	}

	public void setAdmin_username(String admin_username) {
		this.admin_username = admin_username;
	}

	public String getAdmin_password() {
		return admin_password;
	}

	public void setAdmin_password(String admin_password) {
		this.admin_password = admin_password;
	}

	public String getAdmin_mobileNo() {
		return admin_mobileNo;
	}

	public void setAdmin_mobileNo(String admin_mobileNo) {
		this.admin_mobileNo = admin_mobileNo;
	}

	public String getAdmin_emailId() {
		return admin_emailId;
	}

	public void setAdmin_emailId(String admin_emailId) {
		this.admin_emailId = admin_emailId;
	}

	public String getCreation_time() {
		return creation_time;
	}

	public void setCreation_time(String creation_time) {
		this.creation_time = creation_time;
	}

	public String getModification_time() {
		return modification_time;
	}

	public void setModification_time(String modification_time) {
		this.modification_time = modification_time;
	}

	public AdminDto(Long admin_id, String admin_username, String admin_password, String admin_mobileNo,
			String admin_emailId, String creation_time, String modification_time) {
		super();
		this.admin_id = admin_id;
		this.admin_username = admin_username;
		this.admin_password = admin_password;
		this.admin_mobileNo = admin_mobileNo;
		this.admin_emailId = admin_emailId;
		this.creation_time = creation_time;
		this.modification_time = modification_time;
	}

	public AdminDto() {
		super();
		// TODO Auto-generated constructor stub
	}

}
