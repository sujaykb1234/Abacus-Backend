package com.abacus.franchise.dto;

import java.util.Date;

public class ProductDTO {

	private Long productId;
	private String productName;
	private Integer totalProductQuantity;
	private String trackingNumber;
	private Integer requestedQuantity;
	private Integer sentQuantity = 0; // Default to 0
	private Integer remainingQuantity;
	private Date requestTime;
	private Date updateTime;

	public Long getProductId() {
		return productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
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

	public Date getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public ProductDTO(Long productId, String productName, Integer totalProductQuantity, String trackingNumber,
			Integer requestedQuantity, Integer sentQuantity, Integer remainingQuantity, Date requestTime,
			Date updateTime) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.totalProductQuantity = totalProductQuantity;
		this.trackingNumber = trackingNumber;
		this.requestedQuantity = requestedQuantity;
		this.sentQuantity = sentQuantity;
		this.remainingQuantity = remainingQuantity;
		this.requestTime = requestTime;
		this.updateTime = updateTime;
	}

	public ProductDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

}