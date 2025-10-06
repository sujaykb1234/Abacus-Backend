package com.abacus.franchise.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.abacus.franchise.model.Certificate;

@Repository
public interface CertificateRepo extends JpaRepository<Certificate, Long> {

	@Query(value = "SELECT * FROM certificate ORDER BY certificate_number DESC LIMIT 1", nativeQuery = true)
	Optional<Certificate> findTopByOrderByCertificateNumDesc();

	@Query(value = "select * from Certificate where  certificate_id=? and status=true", nativeQuery = true)
	public Optional<Certificate> findByNewId(Long certificate_id);

	@Query(value = "select * from Certificate where status=true", nativeQuery = true)
	public List<Certificate> findAll();

	@Query(value = "select * from certificate where student_id = ?1 and course_id = ?2", nativeQuery = true)
	Optional<Certificate> findByStudentAndCourseId(Long studentId,Long courseId);

	@Query(value = "SELECT * FROM certificate WHERE student_id = :studentId AND course_id = :courseId AND is_generated = true", nativeQuery = true)
	Optional<Certificate> findByStudentIdAndCourseIdAndIsGeneratedTrue(Long studentId, Long courseId);
}
