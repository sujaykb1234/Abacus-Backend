package com.abacus.franchise.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.abacus.franchise.model.Answers;

@Repository
public interface AnswerRepository extends JpaRepository<Answers, Long> {

	@Query(value = "select * from answers where student_id = ? and attempt_id = ? order by id desc limit ?", nativeQuery = true)
	List<Answers> findAllByStudentIdAndExamId(Long studentId, Long examId, int queCount);
}
