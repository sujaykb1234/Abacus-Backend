package com.abacus.franchise.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.abacus.franchise.model.OfflineExam;

@Repository
public interface OfflineExamRepo extends JpaRepository<OfflineExam, Long> {

	
	@Query(value = "select * from offline_exam where status=true", nativeQuery = true)
 	public List<OfflineExam> findAll();
	
	@Query(value = "select * from offline_exam where  offline_exam_id=? and status=true", nativeQuery = true)
	Optional<OfflineExam> findByNewId(Long id);

	
	@Query(value = "select * from offline_exam where  student_id=? ", nativeQuery = true)
	List<OfflineExam> findByStudentId(Long id);
	 

	
	
}
