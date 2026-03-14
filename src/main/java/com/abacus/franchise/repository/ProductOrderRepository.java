package com.abacus.franchise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.abacus.franchise.model.ProductOrderRequest;

public interface ProductOrderRepository extends JpaRepository<ProductOrderRequest, Long> {

    @Query("SELECT COUNT(p) FROM ProductOrderRequest p WHERE p.status = 'PENDING'")
    long countPendingProductRequests();
}
