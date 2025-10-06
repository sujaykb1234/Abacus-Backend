package com.abacus.franchise.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.abacus.franchise.model.PracticeStudent;

@Repository
public interface PracticeStudentRepo extends JpaRepository<PracticeStudent, Long> {

	@Query(value = "select * from  practice_student where student_id = ? and status = true", nativeQuery = true)
	Optional<PracticeStudent> findStudentById(Long student_id);

	@Query(value = "select * from practice_student where email = ? and status = true", nativeQuery = true)
	Optional<PracticeStudent> findByEmail(String email);

	@Query(value = "select * from practice_student where mobile_no = ? and status = true", nativeQuery = true)
	Optional<PracticeStudent> findByMobileNumber(String mobile_no);
}
