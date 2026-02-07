package com.abacus.franchise.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Entity
public class ProductOrderRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "franchise_id")
    private Franchise franchise;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id")
    private Products product;

    @ManyToOne(optional = false)
    @JoinColumn(name = "franchise_order_id")
    private FranchiseOrder franchiseOrder;

    @Column(nullable = false)
    private Integer totalProductQuantity;

    private String trackingNumber;

    @Column(nullable = false)
    private Integer requestedQuantity;

    @Column(nullable = false)
    private Integer sentQuantity = 0;

    private Integer remainingQuantity;

    private LocalDateTime requestTime;
    private LocalDateTime updateTime;

    // ======================
    // JPA CALLBACKS
    // ======================

    @PrePersist
    public void onCreate() {
        this.requestTime = LocalDateTime.now();
        this.updateTime = LocalDateTime.now();
        calculateRemainingQuantity();
    }

    @PreUpdate
    public void onUpdate() {
        this.updateTime = LocalDateTime.now();
        calculateRemainingQuantity();
    }

    private void calculateRemainingQuantity() {
        if (requestedQuantity != null && sentQuantity != null) {
            this.remainingQuantity = requestedQuantity - sentQuantity;
        }
    }

    // ======================
    // GETTERS & SETTERS
    // ======================

    public Long getId() {
        return id;
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

    public LocalDateTime getRequestTime() {
        return requestTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }
}
