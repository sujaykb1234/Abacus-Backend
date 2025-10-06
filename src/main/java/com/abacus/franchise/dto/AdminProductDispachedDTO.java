package com.abacus.franchise.dto;

import java.util.Date;
import java.util.List;

public class AdminProductDispachedDTO {

	private Long franchiseOrderId;
	private Date orderTime;
	private String franchiseName;
	private String franchiseMobile;
	private List<ProductDTO> products; // Updated to hold a list of products
	private String trackingNumber;

	public Long getFranchiseOrderId() {
		return franchiseOrderId;
	}

	public void setFranchiseOrderId(Long franchiseOrderId) {
		this.franchiseOrderId = franchiseOrderId;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public String getFranchiseName() {
		return franchiseName;
	}

	public void setFranchiseName(String franchiseName) {
		this.franchiseName = franchiseName;
	}

	public List<ProductDTO> getProducts() {
		return products;
	}

	public void setProducts(List<ProductDTO> products) {
		this.products = products;
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

	public AdminProductDispachedDTO(Long franchiseOrderId, Date orderTime, String franchiseName, String franchiseMobile,
			List<ProductDTO> products, String trackingNumber) {
		super();
		this.franchiseOrderId = franchiseOrderId;
		this.orderTime = orderTime;
		this.franchiseName = franchiseName;
		this.franchiseMobile = franchiseMobile;
		this.products = products;
		this.trackingNumber = trackingNumber;
	}

	public AdminProductDispachedDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "AdminProductDispachedDTO [franchiseOrderId=" + franchiseOrderId + ", orderTime=" + orderTime
				+ ", franchiseName=" + franchiseName + ", products=" + products + ", trackingNumber=" + trackingNumber
				+ "]";
	}

}
