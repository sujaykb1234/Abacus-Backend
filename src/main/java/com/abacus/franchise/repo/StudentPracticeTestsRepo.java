package com.abacus.franchise.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.abacus.franchise.model.StudentPracticeTests;

@Repository
public interface StudentPracticeTestsRepo extends JpaRepository<StudentPracticeTests, Long> {

	@Query(value = "select count(*) from student_practice_tests where student_id = :studentId", nativeQuery = true)
	int countByStudentId(Long studentId);

	@Query(value = "select * from student_practice_tests where student_id = :studentId and course_id = :courseId", nativeQuery = true)
	List<StudentPracticeTests> findByStudentAndCourse(Long studentId, Long courseId);

}
