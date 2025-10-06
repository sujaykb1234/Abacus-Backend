package com.abacus.franchise.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ProductOrderRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "franchise_id", nullable = false)
	private Franchise franchise;

	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false)
	private Products product;

	@ManyToOne
	@JoinColumn(name = "franchise_order_id", nullable = false)
	private FranchiseOrder franchiseOrder; // Unidirectional mapping to FranchiseOrder

	private Integer totalProductQuantity;
	private String trackingNumber;
	private Integer requestedQuantity;
	private Integer sentQuantity = 0; // Default to 0
	private Integer remainingQuantity;
	private Date requestTime;
	private Date updateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Franchise getFranchise() {
		return franchise;
	}

	public void setFranchise(Franchise franchise) {
		this.franchise = franchise;
	}

	public Products getProduct() {
		return product;
	}

	public void setProduct(Products product) {
		this.product = product;
	}

	public FranchiseOrder getFranchiseOrder() {
		return franchiseOrder;
	}

	public void setFranchiseOrder(FranchiseOrder franchiseOrder) {
		this.franchiseOrder = franchiseOrder;
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

	public Integer getTotalProductQuantity() {
		return totalProductQuantity;
	}

	public void setTotalProductQuantity(Integer totalProductQuantity) {
		this.totalProductQuantity = totalProductQuantity;
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

	public String getTrackingNumber() {
		return trackingNumber;
	}

	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	public ProductOrderRequest(Long id, Franchise franchise, Products product, FranchiseOrder franchiseOrder,
			Integer totalProductQuantity, String trackingNumber, Integer requestedQuantity, Integer sentQuantity,
			Integer remainingQuantity, Date requestTime, Date updateTime) {
		super();
		this.id = id;
		this.franchise = franchise;
		this.product = product;
		this.franchiseOrder = franchiseOrder;
		this.totalProductQuantity = totalProductQuantity;
		this.trackingNumber = trackingNumber;
		this.requestedQuantity = requestedQuantity;
		this.sentQuantity = sentQuantity;
		this.remainingQuantity = remainingQuantity;
		this.requestTime = requestTime;
		this.updateTime = updateTime;
	}

	public ProductOrderRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

}