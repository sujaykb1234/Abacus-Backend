package com.abacus.franchise.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abacus.franchise.model.Counts;


@Repository
public interface CountsRepo extends JpaRepository<Counts, Long>{

}
