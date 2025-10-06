package com.abacus.franchise.model;

import com.abacus.franchise.utility.KitOrderStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class FranchiseKitRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String franchise_name;
	private String franchise_number;
	private String franchise_owner;
	private String courseName;
	private Integer remainingStudents = 0;
	private Integer orderedKits = 0;
	private String franchiseMobile;
	private Integer providedKitsCountToFranchise = 0;
	private Integer avelableTotalKits = 0;
	private Integer remainingKitsSendToFranchise = 0;
	private String trackingNumber;
	private Long courseId;
	private Long franchise_id;
	private String requestedDate;
	private KitOrderStatus kitOrderStatus = KitOrderStatus.PENDING;

	
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

	public String getFranchise_name() {
		return franchise_name;
	}

	public void setFranchise_name(String franchise_name) {
		this.franchise_name = franchise_name;
	}

	public String getFranchise_number() {
		return franchise_number;
	}

	public void setFranchise_number(String franchise_number) {
		this.franchise_number = franchise_number;
	}

	public Long getFranchise_id() {
		return franchise_id;
	}

	public void setFranchise_id(Long franchise_id) {
		this.franchise_id = franchise_id;
	}

	public Integer getRemainingStudents() {
		return remainingStudents;
	}

	public void setRemainingStudents(Integer remainingStudents) {
		this.remainingStudents = remainingStudents;
	}

	public String getTrackingNumber() {
		return trackingNumber;
	}

	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	public String getFranchiseMobile() {
		return franchiseMobile;
	}

	public void setFranchiseMobile(String franchiseMobile) {
		this.franchiseMobile = franchiseMobile;
	}

	public Integer getOrderedKits() {
		return orderedKits;
	}

	public void setOrderedKits(Integer orderedKits) {
		this.orderedKits = orderedKits;
	}

	public Integer getProvidedKitsCountToFranchise() {
		return providedKitsCountToFranchise;
	}

	public void setProvidedKitsCountToFranchise(Integer providedKitsCountToFranchise) {
		this.providedKitsCountToFranchise = providedKitsCountToFranchise;
	}

	public Integer getAvelableTotalKits() {
		return avelableTotalKits;
	}

	public void setAvelableTotalKits(Integer avelableTotalKits) {
		this.avelableTotalKits = avelableTotalKits;
	}

	public Integer getRemainingKitsSendToFranchise() {
		return remainingKitsSendToFranchise;
	}

	public void setRemainingKitsSendToFranchise(Integer remainingKitsSendToFranchise) {
		this.remainingKitsSendToFranchise = remainingKitsSendToFranchise;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public String getRequestedDate() {
		return requestedDate;
	}

	public void setRequestedDate(String requestedDate) {
		this.requestedDate = requestedDate;
	}

	public KitOrderStatus getKitOrderStatus() {
		return kitOrderStatus;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public void setKitOrderStatus(KitOrderStatus kitOrderStatus) {
		this.kitOrderStatus = kitOrderStatus;
	}

	public FranchiseKitRequest(Long id, String franchise_name, String franchise_number, String courseName,
			Integer remainingStudents, Integer orderedKits, String franchiseMobile,
			Integer providedKitsCountToFranchise, Integer avelableTotalKits, Integer remainingKitsSendToFranchise,
			String trackingNumber, Long courseId, Long franchise_id, String requestedDate,
			KitOrderStatus kitOrderStatus) {
		super();
		this.id = id;
		this.franchise_name = franchise_name;
		this.franchise_number = franchise_number;
		this.courseName = courseName;
		this.remainingStudents = remainingStudents;
		this.orderedKits = orderedKits;
		this.franchiseMobile = franchiseMobile;
		this.providedKitsCountToFranchise = providedKitsCountToFranchise;
		this.avelableTotalKits = avelableTotalKits;
		this.remainingKitsSendToFranchise = remainingKitsSendToFranchise;
		this.trackingNumber = trackingNumber;
		this.courseId = courseId;
		this.franchise_id = franchise_id;
		this.requestedDate = requestedDate;
		this.kitOrderStatus = kitOrderStatus;
	}

	public FranchiseKitRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

}
