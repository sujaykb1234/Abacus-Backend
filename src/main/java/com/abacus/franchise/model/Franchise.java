package com.abacus.franchise.model;

import java.util.HashSet;
import java.util.Set;

import com.abacus.franchise.utility.FranchiseStatus;
import com.abacus.franchise.utility.Roles;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Franchise {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long franchise_id;
	private String franchise_number;
	private String franchise_name;
	private String franchise_owner;
	private String mobile_no;
	private String gender;
	private String owner_DOB;
	private String franchise_email;
	private String franchise_password;

	@OneToOne(cascade = CascadeType.ALL)
	private Address franchise_address;

	private String profile_image_name;
	private String profile_image_link;
	private String document_type;
	private String document_number;
	private String document_image_link;
	private String document_image_name;
	private String creation_time;
	private String modification_time;
	private FranchiseStatus franchiseStatus = FranchiseStatus.PENDING;
	
	private Roles roles;
	
	
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Set<Course> courses = new HashSet<>();

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "franchise_exam_attempt", joinColumns = @JoinColumn(name = "franchise_id"), inverseJoinColumns = @JoinColumn(name = "attempt_id"))
	private Set<ExamAttempt> examAttempts = new HashSet<>();
	

	

	public Long getFranchise_id() {
		return franchise_id;
	}

	public void setFranchise_id(Long franchise_id) {
		this.franchise_id = franchise_id;
	}

	public Roles getRoles() {
		return roles;
	}

	public void setRoles(Roles roles) {
		this.roles = roles;
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

	public String getFranchise_owner() {
		return franchise_owner;
	}

	public void setFranchise_owner(String franchise_owner) {
		this.franchise_owner = franchise_owner;
	}

	public String getMobile_no() {
		return mobile_no;
	}

	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getOwner_DOB() {
		return owner_DOB;
	}

	public void setOwner_DOB(String owner_DOB) {
		this.owner_DOB = owner_DOB;
	}

	
	public String getFranchise_email() {
		return franchise_email;
	}

	public void setFranchise_email(String franchise_email) {
		this.franchise_email = franchise_email;
	}

	public String getFranchise_password() {
		return franchise_password;
	}

	public void setFranchise_password(String franchise_password) {
		this.franchise_password = franchise_password;
	}

	public Address getFranchise_address() {
		return franchise_address;
	}

	public void setFranchise_address(Address franchise_address) {
		this.franchise_address = franchise_address;
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

	public String getDocument_type() {
		return document_type;
	}

	public void setDocument_type(String document_type) {
		this.document_type = document_type;
	}

	public String getDocument_number() {
		return document_number;
	}

	public void setDocument_number(String document_number) {
		this.document_number = document_number;
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

	public FranchiseStatus getFranchiseStatus() {
		return franchiseStatus;
	}

	public void setFranchiseStatus(FranchiseStatus franchiseStatus) {
		this.franchiseStatus = franchiseStatus;
	}

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	public Set<ExamAttempt> getExamAttempts() {
		return examAttempts;
	}

	public void setExamAttempts(Set<ExamAttempt> examAttempts) {
		this.examAttempts = examAttempts;
	}

	public void addCourse(Course course) {
		this.courses.add(course);
	}

	public void removeCourse(Course course) {
		this.courses.remove(course);
	}

	public Franchise(Long franchise_id, String franchise_number, String franchise_name, String franchise_owner,
			String mobile_no, String gender, String owner_DOB, String franchise_email,
			String franchise_password, Address franchise_address, String profile_image_name, String profile_image_link,
			String document_type, String document_number, String document_image_link, String document_image_name,
			String creation_time, String modification_time, FranchiseStatus franchiseStatus, Set<Course> courses,
			Set<ExamAttempt> examAttempts) {
		super();
		this.franchise_id = franchise_id;
		this.franchise_number = franchise_number;
		this.franchise_name = franchise_name;
		this.franchise_owner = franchise_owner;
		this.mobile_no = mobile_no;
		this.gender = gender;
		this.owner_DOB = owner_DOB;
		this.franchise_email = franchise_email;
		this.franchise_password = franchise_password;
		this.franchise_address = franchise_address;
		this.profile_image_name = profile_image_name;
		this.profile_image_link = profile_image_link;
		this.document_type = document_type;
		this.document_number = document_number;
		this.document_image_link = document_image_link;
		this.document_image_name = document_image_name;
		this.creation_time = creation_time;
		this.modification_time = modification_time;
		this.franchiseStatus = franchiseStatus;
		this.courses = courses;
		this.examAttempts = examAttempts;
	}

	public Franchise() {
		super();
		// TODO Auto-generated constructor stub
	}

}
