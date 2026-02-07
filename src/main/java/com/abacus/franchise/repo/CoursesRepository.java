package com.abacus.franchise.repo;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.abacus.franchise.dto.CourseDetail;
import com.abacus.franchise.model.Courses;

@Repository
public interface CoursesRepository extends JpaRepository<Courses, UUID> {

	@Query(value = """
		SELECT 
		     c.course_name,c.course_type,c.duration_days,c.no_of_books 
		FROM courses c
		JOIN franchise_courses fc ON fc.course_id = c.course_id
		  WHERE fc.franchise_id = :franchiseId AND courses_status = true;
	""",nativeQuery = true)
	List<CourseDetail> getAllCoursesByFranchiseId(@Param("franchiseId") String franchiseId);
	

	@Query(value="SELECT course_id FROM courses WHERE course_id = :courseId AND is_active = true",nativeQuery = true)
	UUID checkCourseIdIsExistOrNot(@Param("courseId") String courseId);
	
}
