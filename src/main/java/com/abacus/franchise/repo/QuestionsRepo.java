package com.abacus.franchise.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.abacus.franchise.model.Questions;

import jakarta.transaction.Transactional;

@Repository
public interface QuestionsRepo extends JpaRepository<Questions, Long> {

	@Query(value = "select * from questions where course_id =? and status = true", nativeQuery = true)
	List<Questions> findByCourse_Id(Long courseId);
	
	@Query(value = "select * from questions where que_id =? and status = true", nativeQuery = true)
	Optional<Questions> findByQue_Id(Long que_id);

	@Query(value = "SELECT * FROM questions q WHERE status = true and course_id = :courseId AND (question_type = 'TEXT' OR question_type = 'NUMBER') ORDER BY RAND() LIMIT :count", nativeQuery = true)
	List<Questions> findRandomTextQuestions(Long courseId, Integer count);

	@Query(value = "SELECT * FROM questions WHERE status = true and course_id = :courseId AND question_type = 'IMAGE' ORDER BY RAND() LIMIT :count", nativeQuery = true)
	List<Questions> findRandomImageQuestions(Long courseId, Integer count);

	@Query(value = "SELECT * FROM questions WHERE que_id = ? and status = true",nativeQuery = true)
	Optional<Questions> getQuestionsById(Long queId);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE questions SET status = 0 WHERE que_id = ? ",nativeQuery = true)
	public void deleteQuestionsById(Long queId);
}
