package com.abacus.franchise.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ProductSize {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many sizes belong to one product
    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    @JsonBackReference
    private Products product;

    @Column(nullable = false, length = 10)
    private String size; // e.g. S, M, L, XL

    @Column(nullable = false)
    private Integer quantity = 0;

    // ======================
    // GETTERS & SETTERS
    // ======================

    public Long getId() {
        return id;
    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
