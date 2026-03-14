package com.abacus.franchise.repo;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.abacus.franchise.dto.QuestionProjection;
import com.abacus.franchise.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question,UUID>{

	@Query(value = "SELECT correct_answer FROM question WHERE question_id = :questionId",nativeQuery = true)
	String getCorrectAnswerByQuestionId(String questionId);
	
	@Query(value = """
          SELECT DISTINCT
			   q.question_id AS questionId,
			   q.question_type AS questionType,
			   q.question_text AS questionText,
			   q.question_link AS questionLink,
			   q.option_a AS optionA,
			   q.option_b AS optionB,	
			   q.option_c AS optionC,
			   q.option_d AS optionD
	        FROM question q
	        JOIN exam e ON e.exam_id = q.exam_id
	        WHERE q.question_type IN ('IMAGE','TEXT','NUMBER')
	        AND e.course_id = :courseId
	        ORDER BY RAND()
	        LIMIT :limitValue
	        """, nativeQuery = true)

	List<QuestionProjection> getRandomQuestionsByCourseId(
	        @Param("courseId") String courseId,
	        @Param("limitValue") int limitValue
	);
}
