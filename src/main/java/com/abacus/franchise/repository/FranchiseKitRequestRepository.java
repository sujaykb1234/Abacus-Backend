package com.abacus.franchise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.abacus.franchise.model.FranchiseKitRequest;

public interface FranchiseKitRequestRepository extends JpaRepository<FranchiseKitRequest, Long> {

    @Query("SELECT COUNT(k) FROM FranchiseKitRequest k WHERE k.requestType = 'DIFF'")
    long countDiffOrders();

    @Query("SELECT COUNT(k) FROM FranchiseKitRequest k WHERE k.requestType = 'ADDITIONAL'")
    long countAdditionalOrders();
}
