package com.abacus.franchise.repo;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.abacus.franchise.dto.AttemptResultProjection;
import com.abacus.franchise.model.StudentAnswer;

@Repository
public interface StudentAnswerRepository extends JpaRepository<StudentAnswer,UUID> {

	@Query(value = "SELECT exam_attempt FROM student_answer WHERE assign_exam_id = :assignExamId AND student_id = :studentId ORDER BY created_at DESC LIMIT 1",nativeQuery = true)
	Integer getExamAttemptByQuestionIdAndStudentId(String studentId,String assignExamId);

	@Query(value = """
	        SELECT 
	            exam_attempt AS attemptNo,
	            COUNT(question_id) AS totalQuestions,
	            SUM(CASE WHEN is_correct = 1 THEN 1 ELSE 0 END) AS correctQuestions,
	            SUM(CASE WHEN is_correct = 1 THEN 1 ELSE 0 END) AS marks
	        FROM student_answer
	        WHERE student_id = :studentId AND assign_exam_id = :assignExamId AND exam_type = :examType
	        GROUP BY exam_attempt
	        ORDER BY exam_attempt
	        """, nativeQuery = true)
	List<AttemptResultProjection> getAttemptWiseResult(@Param("studentId") String studentId,@Param("assignExamId") String assignExamId,@Param("examType") String examType);
}
