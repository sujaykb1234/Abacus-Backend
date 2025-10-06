package com.abacus.franchise.dto;

import java.util.ArrayList;
import java.util.List;

import com.abacus.franchise.model.Address;
import com.abacus.franchise.model.Course;
import com.abacus.franchise.utility.ExamStatus;
import com.abacus.franchise.utility.ExamType;

public class StudentDTO {

	private Long student_id;
	private String first_name;
	private String last_name;
	private String mobile_no;
	private String email;
	private Long currentExamId;
	private String password;
	private String name_on_certificate;
	private String gender;
	private String dob;
	private Address address;

	private List<Course> courses = new ArrayList<>();
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
	private String franchise_id;
	private String currentCourseName;
	private Long currentCourseId;

	private ExamStatus Examstatus = ExamStatus.PENDING;

	private ExamType examType;

	public Long getStudent_id() {
		return student_id;
	}

	public void setStudent_id(Long student_id) {
		this.student_id = student_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public Long getCurrentExamId() {
		return currentExamId;
	}

	public void setCurrentExamId(Long currentExamId) {
		this.currentExamId = currentExamId;
	}

	public String getCurrentCourseName() {
		return currentCourseName;
	}

	public void setCurrentCourseName(String currentCourseName) {
		this.currentCourseName = currentCourseName;
	}

	public Long getCurrentCourseId() {
		return currentCourseId;
	}

	public void setCurrentCourseId(Long currentCourseId) {
		this.currentCourseId = currentCourseId;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
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

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
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

	public ExamStatus getExamstatus() {
		return Examstatus;
	}

	public void setExamstatus(ExamStatus examstatus) {
		Examstatus = examstatus;
	}

	public ExamType getExamType() {
		return examType;
	}

	public void setExamType(ExamType examType) {
		this.examType = examType;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public StudentDTO(Long student_id, String first_name, String last_name, String mobile_no, String email,
			Long currentExamId, String password, String name_on_certificate, String gender, String dob, Address address,
			List<Course> courses, String profile_image_name, String profile_image_link, String education, String board,
			String document_image_link, String document_image_name, String creation_time, String modification_time,
			boolean status, String franchiseName, String franchise_id, String currentCourseName, Long currentCourseId,
			ExamStatus examstatus, ExamType examType) {
		super();
		this.student_id = student_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.mobile_no = mobile_no;
		this.email = email;
		this.currentExamId = currentExamId;
		this.password = password;
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
		this.franchise_id = franchise_id;
		this.currentCourseName = currentCourseName;
		this.currentCourseId = currentCourseId;
		Examstatus = examstatus;
		this.examType = examType;
	}

	public StudentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

}