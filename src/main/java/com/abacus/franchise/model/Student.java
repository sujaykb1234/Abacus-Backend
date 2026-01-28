package com.abacus.franchise.model;

import java.util.ArrayList;
import java.util.List;

import com.abacus.franchise.utility.ExamStatus;
import com.abacus.franchise.utility.ExamType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long student_id;
	private String first_name;
	private String last_name;
	private String mobile_no;
	private String email;
	private String password;
	private String name_on_certificate;
	private String gender;
	private String dob;
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;
	private Long currentExamId;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "student_course", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
	private List<Course> courses = new ArrayList<>();
	private String profile_image_name;
	private String profile_image_link;
	private String education;
	private String board;
	private String creation_time;
	private String modification_time;
	private String exam_password;
	private Long currentCourseId;
	private String currentCourseName;
	private ExamStatus examStatus = ExamStatus.PENDING;
	private boolean status = true;
	@ManyToOne
	@JoinColumn(name = "franchise_id")
	private Franchise franchise;

	private ExamType examType;

	@JsonIgnore
	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<StudentExam> studentExams = new ArrayList<>(); // Added relationship with StudentExam

	public void addCourse(Course course) {
		if (course == null) {
			throw new IllegalArgumentException("Course cannot be null");
		}
		if (!this.courses.contains(course)) {
			this.courses.add(course);
		}
	}

	public Long getCurrentExamId() {
		return currentExamId;
	}

	public void setCurrentExamId(Long currentExamId) {
		this.currentExamId = currentExamId;
	}

	public void removeCourse(Course course) {
		if (this.courses != null && this.courses.contains(course)) {
			this.courses.remove(course);
		}
	}

	public List<StudentExam> getStudentExams() {
		return studentExams;
	}

	public void setStudentExams(List<StudentExam> studentExams) {
		this.studentExams = studentExams;
	}

	public Long getStudent_id() {
		return student_id;
	}

	public void setStudent_id(Long student_id) {
		this.student_id = student_id;
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

	public String getExam_password() {
		return exam_password;
	}

	public void setExam_password(String exam_password) {
		this.exam_password = exam_password;
	}

	public ExamStatus getExamStatus() {
		return examStatus;
	}

	public void setExamStatus(ExamStatus examStatus) {
		this.examStatus = examStatus;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Franchise getFranchise() {
		return franchise;
	}

	public void setFranchise(Franchise franchise) {
		this.franchise = franchise;
	}

	public ExamType getExamType() {
		return examType;
	}

	public void setExamType(ExamType examType) {
		this.examType = examType;
	}
	public Student(Long student_id, String first_name, String last_name, String mobile_no, String email,
			String password, String name_on_certificate, String gender, String dob, Address address, Long currentExamId,
			List<Course> courses, String profile_image_name, String profile_image_link, String education, String board,
			 String creation_time, String modification_time,
			String exam_password, Long currentCourseId, String currentCourseName, ExamStatus examStatus, boolean status,
			Franchise franchise, ExamType examType, List<StudentExam> studentExams) {
		super();
		this.student_id = student_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.mobile_no = mobile_no;
		this.email = email;
		this.password = password;
		this.name_on_certificate = name_on_certificate;
		this.gender = gender;
		this.dob = dob;
		this.address = address;
		this.currentExamId = currentExamId;
		this.courses = courses;
		this.profile_image_name = profile_image_name;
		this.profile_image_link = profile_image_link;
		this.education = education;
		this.board = board;
		this.creation_time = creation_time;
		this.modification_time = modification_time;
		this.exam_password = exam_password;
		this.currentCourseId = currentCourseId;
		this.currentCourseName = currentCourseName;
		this.examStatus = examStatus;
		this.status = status;
		this.franchise = franchise;
		this.examType = examType;
		this.studentExams = studentExams;
	}

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

}