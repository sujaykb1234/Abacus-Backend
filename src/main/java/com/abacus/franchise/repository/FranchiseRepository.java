package com.abacus.franchise.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.abacus.franchise.model.Franchise;

public interface FranchiseRepository extends JpaRepository<Franchise, Long> {
    
    @Query("SELECT COUNT(f) FROM Franchise f")
    long countPendingRequests();
}
 