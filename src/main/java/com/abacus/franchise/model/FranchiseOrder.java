package com.abacus.franchise.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "franchise_order")
public class FranchiseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "franchise_id", nullable = false)
    private Franchise franchise;

    @Column(name = "order_time", nullable = false)
    private LocalDateTime orderTime;

    // ===== Constructors =====

    public FranchiseOrder() {
        // JPA requires no-args constructor
    }

    public FranchiseOrder(Franchise franchise, LocalDateTime orderTime) {
        this.franchise = franchise;
        this.orderTime = orderTime;
    }

    // ===== Getters & Setters =====

}
