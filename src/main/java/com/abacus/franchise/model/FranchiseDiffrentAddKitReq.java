package com.abacus.franchise.model;

import com.abacus.franchise.utility.KitOrderStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class FranchiseDiffrentAddKitReq {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String studentName;
	private String courseName;
	private Long studentId;
	private Long franchiseId;
	private String franchiseName;
	private String franchise_owner;
	private String franchiseMobile;
	private String trackingNumber;
	private String address;
	private KitOrderStatus kitOrderStatus = KitOrderStatus.PENDING;
	private Long courseId;
	
	public String getFranchise_owner() {
		return franchise_owner;
	}

	public void setFranchise_owner(String franchise_owner) {
		this.franchise_owner = franchise_owner;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTrackingNumber() {
		return trackingNumber;
	}

	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getFranchiseMobile() {
		return franchiseMobile;
	}

	public void setFranchiseMobile(String franchiseMobile) {
		this.franchiseMobile = franchiseMobile;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public Long getFranchiseId() {
		return franchiseId;
	}

	public void setFranchiseId(Long franchiseId) {
		this.franchiseId = franchiseId;
	}

	public KitOrderStatus getKitOrderStatus() {
		return kitOrderStatus;
	}

	public void setKitOrderStatus(KitOrderStatus kitOrderStatus) {
		this.kitOrderStatus = kitOrderStatus;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public String getFranchiseName() {
		return franchiseName;
	}

	public void setFranchiseName(String franchiseName) {
		this.franchiseName = franchiseName;
	}

	public FranchiseDiffrentAddKitReq(Long id, String studentName, String courseName, Long studentId, Long franchiseId,
			String franchiseName, String franchiseMobile, String trackingNumber, String address,
			KitOrderStatus kitOrderStatus, Long courseId) {
		super();
		this.id = id;
		this.studentName = studentName;
		this.courseName = courseName;
		this.studentId = studentId;
		this.franchiseId = franchiseId;
		this.franchiseName = franchiseName;
		this.franchiseMobile = franchiseMobile;
		this.trackingNumber = trackingNumber;
		this.address = address;
		this.kitOrderStatus = kitOrderStatus;
		this.courseId = courseId;
	}

	public FranchiseDiffrentAddKitReq() {
		super();
		// TODO Auto-generated constructor stub
	}

}
