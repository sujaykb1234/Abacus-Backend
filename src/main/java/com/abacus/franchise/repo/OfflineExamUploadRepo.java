package com.abacus.franchise.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.abacus.franchise.model.OfflineExamUpload;

@Repository
public interface OfflineExamUploadRepo extends JpaRepository<OfflineExamUpload, Long> {

	@Query(value = "select * from offline_exam_upload where course_id = :courseId", nativeQuery = true)
	Optional<OfflineExamUpload> findByCourseId(Long courseId);

}
