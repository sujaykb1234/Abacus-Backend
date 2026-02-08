package com.abacus.franchise.repo;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.abacus.franchise.dto.CourseDetail;
import com.abacus.franchise.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, UUID> {

//	@Query(value = """
//		SELECT 
//		     c.course_name,c.course_type,c.duration_days,c.no_of_books 
//		FROM course c
//		JOIN franchise_course fc ON fc.course_id = c.course_id
//		  WHERE fc.franchise_id = :franchiseId AND courses_status = true;
//	""",nativeQuery = true)

	@Query(value="""
		SELECT 
		    c.course_id,
		    c.course_name,
		    c.course_type,
		    c.duration_days,
		    c.no_of_books,
		    COALESCE(SUM(kor.kit_count),0) AS kit_count
		FROM course c
		JOIN franchise_course fc 
		    ON fc.course_id = c.course_id
		LEFT JOIN kit_requests kr 
		    ON kr.franchise_id = fc.franchise_id
		LEFT JOIN kit_order_item kor 
		    ON kor.kit_request_id = kr.kit_request_id
		   AND kor.course_id = c.course_id
		WHERE fc.franchise_id = :franchiseId
		AND fc.courses_status = 1
		GROUP BY 
		    c.course_id,
		    c.course_name,
		    c.course_type,
		    c.duration_days,
		    c.no_of_books;
	""",nativeQuery = true)
	List<CourseDetail> getAllCoursesByFranchiseId(@Param("franchiseId") String franchiseId);
	

	@Query(value="SELECT course_id FROM course WHERE course_id = :courseId AND is_active = true",nativeQuery = true)
	UUID checkCourseIdIsExistOrNot(@Param("courseId") String courseId);
	
}
