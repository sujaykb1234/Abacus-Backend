package com.abacus.franchise.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class PracticeStudent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long student_id;
	private String name;
	private String mobile_no;
	private String email;
	private String password;
	private String gender;
	private String dob;
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;
	private String profile_image_name;
	private String profile_image_link;
	private String document_image_link;
	private String document_image_name;
	private String creation_time;
	private String modification_time;
	private boolean status = true;

	public Long getStudent_id() {
		return student_id;
	}

	public void setStudent_id(Long student_id) {
		this.student_id = student_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile_no() {
		return mobile_no;
	}

	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getProfile_image_name() {
		return profile_image_name;
	}

	public void setProfile_image_name(String profile_image_name) {
		this.profile_image_name = profile_image_name;
	}

	public String getProfile_image_link() {
		return profile_image_link;
	}

	public void setProfile_image_link(String profile_image_link) {
		this.profile_image_link = profile_image_link;
	}

	public String getDocument_image_link() {
		return document_image_link;
	}

	public void setDocument_image_link(String document_image_link) {
		this.document_image_link = document_image_link;
	}

	public String getDocument_image_name() {
		return document_image_name;
	}

	public void setDocument_image_name(String document_image_name) {
		this.document_image_name = document_image_name;
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

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public PracticeStudent(Long student_id, String name, String mobile_no, String email, String password, String gender,
			String dob, Address address, String profile_image_name, String profile_image_link,
			String document_image_link, String document_image_name, String creation_time, String modification_time,
			boolean status) {
		super();
		this.student_id = student_id;
		this.name = name;
		this.mobile_no = mobile_no;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.dob = dob;
		this.address = address;
		this.profile_image_name = profile_image_name;
		this.profile_image_link = profile_image_link;
		this.document_image_link = document_image_link;
		this.document_image_name = document_image_name;
		this.creation_time = creation_time;
		this.modification_time = modification_time;
		this.status = status;
	}

	public PracticeStudent() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "PracticeStudent [student_id=" + student_id + ", name=" + name + ", mobile_no=" + mobile_no + ", email="
				+ email + ", password=" + password + ", gender=" + gender + ", dob=" + dob + ", address=" + address
				+ ", profile_image_name=" + profile_image_name + ", profile_image_link=" + profile_image_link
				+ ", document_image_link=" + document_image_link + ", document_image_name=" + document_image_name
				+ ", creation_time=" + creation_time + ", modification_time=" + modification_time + ", status=" + status
				+ "]";
	}
	
	

}
