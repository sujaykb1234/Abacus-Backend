package com.abacus.franchise.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.abacus.franchise.model.ExamAttempt;

import jakarta.transaction.Transactional;

@Repository
public interface ExamAttemptRepository extends JpaRepository<ExamAttempt, Long> {

	@Query(value = "select * from exam_attempt where student_id = ? and status = true", nativeQuery = true)
	List<ExamAttempt> findByStudent_StudentId(Long studentId);

	@Query(value = "select * from exam_attempt where course_id = ? and status = true", nativeQuery = true)
	List<ExamAttempt> findByCourseId(Long course_id);

	@Query(value = "select * from exam_attempt where course_id = ?1 and exam_name = ?2 and status = true", nativeQuery = true)
	Optional<ExamAttempt> findUsingTheCouserseIdAndExamName(Long courseId, String examaName);

	@Query(value = "SELECT * FROM exam_attempt e WHERE course_id = :courseId AND student_id = :studentId and status = true", nativeQuery = true)
	List<ExamAttempt> findByCourseIdAndStudentId(@Param("courseId") Long courseId, @Param("studentId") Long studentId);

	@Query(value = "SELECT * FROM exam_attempt where franchise_id = ? and status = true", nativeQuery = true)
	List<ExamAttempt> findByFranchiseId(Long franchiseId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE exam_attempt set status = 0 where attempt_id = ?",nativeQuery = true)
    public void deleteExamById(Long attemptId);

    @Query(value = "SELECT * from  exam_attempt where attempt_id = ? and status = true",nativeQuery = true)
    public Optional<ExamAttempt> getExamById(Long attemptId);


}
