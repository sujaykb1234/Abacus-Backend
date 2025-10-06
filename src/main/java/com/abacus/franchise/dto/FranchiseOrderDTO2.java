package com.abacus.franchise.dto;

import java.util.Date;
import java.util.List;

public class FranchiseOrderDTO2 {
	private Long orderId;
	private Date orderTime;
	private String franchiseName;
	private List<ProductOrderRequestDTO> productOrders;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
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

	public List<ProductOrderRequestDTO> getProductOrders() {
		return productOrders;
	}

	public void setProductOrders(List<ProductOrderRequestDTO> productOrders) {
		this.productOrders = productOrders;
	}

	public FranchiseOrderDTO2(Long orderId, Date orderTime, String franchiseName,
			List<ProductOrderRequestDTO> productOrders) {
		super();
		this.orderId = orderId;
		this.orderTime = orderTime;
		this.franchiseName = franchiseName;
		this.productOrders = productOrders;
	}

	public FranchiseOrderDTO2() {
		super();
		// TODO Auto-generated constructor stub
	}

}
