package com.abacus.franchise.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "product_order_request")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductOrderRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "remaining_quantity")
    private Integer remainingQuantity;

    @Column(name = "request_time")
    private LocalDateTime requestTime;

    @Column(name = "requested_quantity")
    private Integer requestedQuantity;

    @Column(name = "sent_quantity")
    private Integer sentQuantity;

    @Column(name = "total_product_quantity")
    private Integer totalProductQuantity;

    @Column(name = "tracking_number", length = 255)
    private String trackingNumber;

    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @Column(name = "franchise_id", nullable = false)
    private Long franchiseId;

    @Column(name = "franchise_order_id", nullable = false)
    private Long franchiseOrderId;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    // Relationships (non-owning side)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "franchise_id", insertable = false, updatable = false)
    private Franchise franchise;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "franchise_order_id", insertable = false, updatable = false)
    private FranchiseOrder franchiseOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;
}
