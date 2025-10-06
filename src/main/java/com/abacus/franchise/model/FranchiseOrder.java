package com.abacus.franchise.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class FranchiseOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "franchise_id", nullable = false)
	private Franchise franchise;

	private Date orderTime;

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

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public FranchiseOrder(Long id, Franchise franchise, Date orderTime) {
		super();
		this.id = id;
		this.franchise = franchise;
		this.orderTime = orderTime;
	}

	public FranchiseOrder() {
		super();
		// TODO Auto-generated constructor stub
	}

}