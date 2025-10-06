package com.abacus.franchise.dto;

public class StudentResponseDTO {

	private Long id;
	private Long student_id;
	private String franchiseName;
	private String currentCourseName;
	private Long currentCourseId;
	private String creation_time;
	private String dob;
	private String password;
	private String mobile_no;
	private String email;
	private String first_name;
	private String courseExamStatus;
	private String last_name;
	private String franchise_owner;
	private String profile_image_link;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getStudent_id() {
		return student_id;
	}

	public void setStudent_id(Long student_id) {
		this.student_id = student_id;
	}

	public String getFranchiseName() {
		return franchiseName;
	}

	public void setFranchiseName(String franchiseName) {
		this.franchiseName = franchiseName;
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

	public String getCreation_time() {
		return creation_time;
	}

	public void setCreation_time(String creation_time) {
		this.creation_time = creation_time;
	}

	public String getFranchise_owner() {
		return franchise_owner;
	}

	public void setFranchise_owner(String franchise_owner) {
		this.franchise_owner = franchise_owner;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
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

	public String getFirst_name() {
		return first_name;
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

	public String getCourseExamStatus() {
		return courseExamStatus;
	}

	public void setCourseExamStatus(String courseExamStatus) {
		this.courseExamStatus = courseExamStatus;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getProfile_image_link() {
		return profile_image_link;
	}

	public void setProfile_image_link(String profile_image_link) {
		this.profile_image_link = profile_image_link;
	}

	public StudentResponseDTO(Long id, Long student_id, String franchiseName, String currentCourseName,
			Long currentCourseId, String creation_time, String dob, String password, String mobile_no, String email,
			String first_name, String courseExamStatus, String last_name, String franchise_owner) {
		super();
		this.id = id;
		this.student_id = student_id;
		this.franchiseName = franchiseName;
		this.currentCourseName = currentCourseName;
		this.currentCourseId = currentCourseId;
		this.creation_time = creation_time;
		this.dob = dob;
		this.password = password;
		this.mobile_no = mobile_no;
		this.email = email;
		this.first_name = first_name;
		this.courseExamStatus = courseExamStatus;
		this.last_name = last_name;
		this.franchise_owner = franchise_owner;
	}

	public StudentResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

}
