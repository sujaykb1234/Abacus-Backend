package com.abacus.franchise.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.abacus.franchise.model.State;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {

	@Query(value="select * from state",nativeQuery = true)
	public List<State> getAllState();
	
}
