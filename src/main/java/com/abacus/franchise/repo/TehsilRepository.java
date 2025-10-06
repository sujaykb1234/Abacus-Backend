package com.abacus.franchise.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abacus.franchise.model.District;
import com.abacus.franchise.model.State;

@Repository
public interface TehsilRepository extends JpaRepository<District, Long> {

}
