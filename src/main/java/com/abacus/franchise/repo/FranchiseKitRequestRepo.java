package com.abacus.franchise.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.abacus.franchise.model.FranchiseKitRequest;

@Repository
public interface FranchiseKitRequestRepo extends JpaRepository<FranchiseKitRequest, Long> {

	@Query(value = "select * from franchise_kit_request where remaining_kits_send_to_franchise > 0 order by id desc", nativeQuery = true)
	List<FranchiseKitRequest> findAllKitRequests();

	@Query(value = "SELECT * FROM franchise_kit_request WHERE franchise_id = :franchiseId AND course_id = :courseId order by id desc", nativeQuery = true)
	Optional<FranchiseKitRequest> findByFranchiseIdAndCourseId(Long franchiseId, Long courseId);

	@Query(value = "SELECT f.tracking_number FROM franchise_kit_request f WHERE f.tracking_number LIKE :pattern ORDER BY f.tracking_number DESC", nativeQuery = true)
	List<String> findLastTrackingNumber(String pattern);

}
