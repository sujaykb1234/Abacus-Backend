package com.abacus.franchise.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.abacus.franchise.model.FranchiseDiffrentAddKitReq;

@Repository
public interface DiffrentAddKitReqRepo extends JpaRepository<FranchiseDiffrentAddKitReq, Long> {

	@Query(value = "select * from franchise_diffrent_add_kit_req where student_id = :studentId and course_id = :courseId order by id desc", nativeQuery = true)
	Optional<FranchiseDiffrentAddKitReq> findByStudentIdAndCourseId(Long studentId, Long courseId);

	@Query(value = "select * from franchise_diffrent_add_kit_req where student_id = :studentId and course_id = :courseId order by id desc", nativeQuery = true)
	Optional<FranchiseDiffrentAddKitReq> findByFranchiseAndCourse(Long studentId, Long courseId);

	@Query(value = "select * from franchise_diffrent_add_kit_req where kit_order_status = 1", nativeQuery = true)
	List<FranchiseDiffrentAddKitReq> findAllOrderedRequest();

}
