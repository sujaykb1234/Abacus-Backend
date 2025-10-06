package com.abacus.franchise.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.abacus.franchise.model.FranchiseKitRequestsLogs;

@Repository
public interface FranchiseKitRequestsLogsRepo extends JpaRepository<FranchiseKitRequestsLogs, Long> {

	@Query(value = "SELECT * FROM franchise_kit_requests_logs f WHERE f.franchise_id = :franchiseId AND DATE(f.requested_date) BETWEEN :startDate AND :endDate", nativeQuery = true)
	List<FranchiseKitRequestsLogs> findAllByRequestedDateBetweenAndFranchiseId(@Param("franchiseId") Long franchiseId,
			@Param("startDate") String startDate, @Param("endDate") String endDate);

	@Query(value = "SELECT * FROM franchise_kit_requests_logs f WHERE DATE(f.requested_date) BETWEEN :startDate AND :endDate", nativeQuery = true)
	List<FranchiseKitRequestsLogs> findAllByRequestedDateBetweenWithoutFranchiseId(@Param("startDate") String startDate,
			@Param("endDate") String endDate);
	
	@Query(value = "SELECT * FROM franchise_kit_requests_logs where franchise_id= :franchiseId", nativeQuery = true)
	List<FranchiseKitRequestsLogs> findAllKitRequestByFranchiseId(@Param("franchiseId") Long franchiseId);
}
