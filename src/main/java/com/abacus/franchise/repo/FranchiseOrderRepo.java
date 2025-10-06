package com.abacus.franchise.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abacus.franchise.model.FranchiseOrder;

@Repository
public interface FranchiseOrderRepo   extends JpaRepository<FranchiseOrder, Long> {
}
