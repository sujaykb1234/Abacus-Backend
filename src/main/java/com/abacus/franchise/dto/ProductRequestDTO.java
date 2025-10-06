package com.abacus.franchise.dto;

import java.util.Date;

public class ProductRequestDTO {

	private Long id;
	private Long productId;
	private String productName;
	private Integer requestedQuantity;
	private Integer sentQuantity;
	private Integer remainingQuantity;
	private Long franchiseId;
	private String franchiseName;
	private Date requestDate;
	private Integer totalProductQuantity;
	private String trackingNumber;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getRequestedQuantity() {
		return requestedQuantity;
	}

	public void setRequestedQuantity(Integer requestedQuantity) {
		this.requestedQuantity = requestedQuantity;
	}

	public Integer getSentQuantity() {
		return sentQuantity;
	}

	public void setSentQuantity(Integer sentQuantity) {
		this.sentQuantity = sentQuantity;
	}

	public Integer getRemainingQuantity() {
		return remainingQuantity;
	}

	public void setRemainingQuantity(Integer remainingQuantity) {
		this.remainingQuantity = remainingQuantity;
	}

	public Long getFranchiseId() {
		return franchiseId;
	}

	public void setFranchiseId(Long franchiseId) {
		this.franchiseId = franchiseId;
	}

	public String getFranchiseName() {
		return franchiseName;
	}

	public void setFranchiseName(String franchiseName) {
		this.franchiseName = franchiseName;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public Integer getTotalProductQuantity() {
		return totalProductQuantity;
	}

	public void setTotalProductQuantity(Integer totalProductQuantity) {
		this.totalProductQuantity = totalProductQuantity;
	}

	public String getTrackingNumber() {
		return trackingNumber;
	}

	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	public ProductRequestDTO(Long id, Long productId, String productName, Integer requestedQuantity,
			Integer sentQuantity, Integer remainingQuantity, Long franchiseId, String franchiseName, Date requestDate,
			Integer totalProductQuantity, String trackingNumber) {
		super();
		this.id = id;
		this.productId = productId;
		this.productName = productName;
		this.requestedQuantity = requestedQuantity;
		this.sentQuantity = sentQuantity;
		this.remainingQuantity = remainingQuantity;
		this.franchiseId = franchiseId;
		this.franchiseName = franchiseName;
		this.requestDate = requestDate;
		this.totalProductQuantity = totalProductQuantity;
		this.trackingNumber = trackingNumber;
	}

	public ProductRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

}
