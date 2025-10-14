package com.abacus.franchise.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.abacus.franchise.model.District;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {

	@Query(value = "SELECT * FROM district where state_state_id = ?",nativeQuery =  true)
	List<District> getAllDistrictByStateId(Long stateId);
	
	
}
