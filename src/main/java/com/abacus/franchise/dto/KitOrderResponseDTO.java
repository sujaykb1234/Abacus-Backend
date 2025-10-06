package com.abacus.franchise.dto;

public class KitOrderResponseDTO {

	private Long id;
	private String franchise_name;
	private String franchise_number;
	private String courseName;
	private Integer remainingStudents;
	private Integer orderedKits;
	private String franchiseMobile;
	private Integer providedKitsCountToFranchise;
	private Integer avelableTotalKits;
	private Integer remainingKitsSendToFranchise;
	private String trackingNumber;
	private Long courseId;
	private Long franchise_id;
	private String requestedDate;
	private String kitOrderStatus;

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

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Integer getRemainingStudents() {
		return remainingStudents;
	}

	public void setRemainingStudents(Integer remainingStudents) {
		this.remainingStudents = remainingStudents;
	}

	public Integer getOrderedKits() {
		return orderedKits;
	}

	public void setOrderedKits(Integer orderedKits) {
		this.orderedKits = orderedKits;
	}

	public String getFranchiseMobile() {
		return franchiseMobile;
	}

	public void setFranchiseMobile(String franchiseMobile) {
		this.franchiseMobile = franchiseMobile;
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

	public String getTrackingNumber() {
		return trackingNumber;
	}

	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public Long getFranchise_id() {
		return franchise_id;
	}

	public void setFranchise_id(Long franchise_id) {
		this.franchise_id = franchise_id;
	}

	public String getRequestedDate() {
		return requestedDate;
	}

	public void setRequestedDate(String requestedDate) {
		this.requestedDate = requestedDate;
	}

	public String getKitOrderStatus() {
		return kitOrderStatus;
	}

	public void setKitOrderStatus(String kitOrderStatus) {
		this.kitOrderStatus = kitOrderStatus;
	}

}
