package com.abacus.franchise.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.abacus.franchise.model.StudentEnrollment;

@Repository
public interface StudentEnrollmentRepo extends JpaRepository<StudentEnrollment, Long> {

	@Query(value = "select * from student_enrollment where student_id = :studentId", nativeQuery = true)
	List<StudentEnrollment> findByStudentId(Long studentId);

	@Query(value = "SELECT * FROM student_enrollment WHERE student_id = :studentId AND enrollment_status = :enrollmentStatus", nativeQuery = true)
	List<StudentEnrollment> findByStudentIdAndEnrollmentStatus(@Param("studentId") Long studentId,
			@Param("enrollmentStatus") String enrollmentStatus);

	@Query(value = "SELECT * FROM student_enrollment WHERE student_id = :studentId AND course_id = :courseId AND enrollment_status = :enrollmentStatus", nativeQuery = true)
	Optional<StudentEnrollment> findByStudentIdAndCourseIdAndEnrollmentStatus(@Param("studentId") Long studentId,
			@Param("courseId") Long courseId, @Param("enrollmentStatus") String enrollmentStatus);

	@Query(value = "SELECT * FROM student_enrollment WHERE student_id = :studentId AND enrollment_status IN :statuses", nativeQuery = true)
	List<StudentEnrollment> findByStudentIdAndEnrollmentStatusIn(@Param("studentId") Long studentId,
			@Param("statuses") List<String> statuses);
}
