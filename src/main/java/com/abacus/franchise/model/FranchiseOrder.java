package com.abacus.franchise.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "franchise_order")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FranchiseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "order_time")
    private LocalDateTime orderTime;

    @Column(name = "franchise_id", nullable = false)
    private Long franchiseId;

    // Relationship (non-owning side)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "franchise_id", insertable = false, updatable = false)
    private Franchise franchise;
}
