package com.abacus.franchise.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.abacus.franchise.model.CourseImage;
import com.abacus.franchise.model.SliderImage;


@Repository
public interface CourseImageRepository extends JpaRepository<CourseImage, Long>{

	@Query(value = "select * from course_image where course_id=?" , nativeQuery = true)
	List<CourseImage> findByCourseId(Long courseId);

	
}
