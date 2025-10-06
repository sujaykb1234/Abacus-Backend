package com.abacus.franchise.dto;

import java.util.ArrayList;
import java.util.List;

import com.abacus.franchise.model.Address;
import com.abacus.franchise.utility.ExamStatus;
import com.abacus.franchise.utility.ExamType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToOne;

public class stdDTO {

	private Long student_id;
	private String first_name;
	private String last_name;
	private String mobile_no;
	private String email;
	private String password;
	private Long currentExamId;
	private String name_on_certificate;
	private String gender;
	private String dob;
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;

	private List<CourseDTO> courses = new ArrayList<>();
	private String profile_image_name;
	private String profile_image_link;
	private String education;
	private String board;
	private String document_image_link;
	private String document_image_name;
	private String creation_time;
	private String modification_time;
	private boolean status = true;
	private String franchiseName;
	private String userName;
	private String franchise_id;
	private Long currentCourseId;
	private String currentCourseName;

	private ExamStatus Examstatus;

	private ExamType examType;

	public Long getStudent_id() {
		return student_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setStudent_id(Long student_id) {
		this.student_id = student_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public Long getCurrentCourseId() {
		return currentCourseId;
	}

	public void setCurrentCourseId(Long currentCourseId) {
		this.currentCourseId = currentCourseId;
	}

	public String getCurrentCourseName() {
		return currentCourseName;
	}

	public void setCurrentCourseName(String currentCourseName) {
		this.currentCourseName = currentCourseName;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
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

	public String getName_on_certificate() {
		return name_on_certificate;
	}

	public void setName_on_certificate(String name_on_certificate) {
		this.name_on_certificate = name_on_certificate;
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

	public List<CourseDTO> getCourses() {
		return courses;
	}

	public void setCourses(List<CourseDTO> courses) {
		this.courses = courses;
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

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getBoard() {
		return board;
	}

	public void setBoard(String board) {
		this.board = board;
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

	public Long getCurrentExamId() {
		return currentExamId;
	}

	public void setCurrentExamId(Long currentExamId) {
		this.currentExamId = currentExamId;
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

	public String getFranchiseName() {
		return franchiseName;
	}

	public void setFranchiseName(String franchiseName) {
		this.franchiseName = franchiseName;
	}

	public String getFranchise_id() {
		return franchise_id;
	}

	public void setFranchise_id(String franchise_id) {
		this.franchise_id = franchise_id;
	}

	public ExamType getExamType() {
		return examType;
	}

	public void setExamType(ExamType examType) {
		this.examType = examType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public ExamStatus getExamstatus() {
		return Examstatus;
	}

	public void setExamstatus(ExamStatus examstatus) {
		Examstatus = examstatus;
	}

	public stdDTO(Long student_id, String first_name, String last_name, String mobile_no, String email, String password,
			Long currentExamId, String name_on_certificate, String gender, String dob, Address address,
			List<CourseDTO> courses, String profile_image_name, String profile_image_link, String education,
			String board, String document_image_link, String document_image_name, String creation_time,
			String modification_time, boolean status, String franchiseName, String userName, String franchise_id,
			Long currentCourseId, String currentCourseName, ExamStatus examstatus, ExamType examType) {
		super();
		this.student_id = student_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.mobile_no = mobile_no;
		this.email = email;
		this.password = password;
		this.currentExamId = currentExamId;
		this.name_on_certificate = name_on_certificate;
		this.gender = gender;
		this.dob = dob;
		this.address = address;
		this.courses = courses;
		this.profile_image_name = profile_image_name;
		this.profile_image_link = profile_image_link;
		this.education = education;
		this.board = board;
		this.document_image_link = document_image_link;
		this.document_image_name = document_image_name;
		this.creation_time = creation_time;
		this.modification_time = modification_time;
		this.status = status;
		this.franchiseName = franchiseName;
		this.userName = userName;
		this.franchise_id = franchise_id;
		this.currentCourseId = currentCourseId;
		this.currentCourseName = currentCourseName;
		Examstatus = examstatus;
		this.examType = examType;
	}

	public stdDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

}
