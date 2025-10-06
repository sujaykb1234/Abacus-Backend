package com.abacus.franchise.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.abacus.franchise.dto.CourseDTO;
import com.abacus.franchise.model.Course;

@Repository
public interface CourseRepo extends JpaRepository<Course, Long> {

	@Query(value = "select * from course where course_name  LIKE CONCAT(?1, '%') ", nativeQuery = true)
	List<Course> findTheCourceUsingTheName(String name);

	@Query(value = "select * from course where course_id = ? and course_status = true", nativeQuery = true)
	Optional<Course> findcourseById(Long course_id);
	
	
	@Query(value = "select * from course where course_id = ? and course_status = true", nativeQuery = true)
	Optional<CourseDTO> findcoursesById(Long course_id);
	
	
	@Query(value = "select * from course where course_status = true", nativeQuery = true)
	List<Course> findAllCourses();
	
	

	
	

}
