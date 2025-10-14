package com.abacus.franchise.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.abacus.franchise.model.Student;

import jakarta.transaction.Transactional;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {

	@Query(value = "select * from student where email = ? and status = true  order by student_id desc limit 1", nativeQuery = true)
	Optional<Student> findByEmial(String email);

	@Query(value = "select * from student where mobile_no = ? and status = true  order by student_id desc limit 1", nativeQuery = true)
	Optional<Student> findByMobileNumber(String mobile_no);

	@Query(value = "select * from student where mobile_no = ? and status = true  order by student_id desc limit 1", nativeQuery = true)
	Optional<Student> findByusername(String studentUsername);

	@Query(value = "select * from student where email = ? and status = true  order by student_id desc limit 1", nativeQuery = true)
	Optional<Student> findByEmail(String mailTo);

	@Query(value = "select * from student where email = ? and status = true  order by student_id desc limit 1", nativeQuery = true)
	Optional<Student> findByusernameForPassword(String loginusername);

	@Query(value = "SELECT COUNT(*) FROM student", nativeQuery = true)
	Long getAllStudentsCounts();

	@Query(value = "select * from student where status = true", nativeQuery = true)
	Page<Student> getAllStudent(Pageable page);

	@Query(value = "select * from student where student_id = ? and status = true", nativeQuery = true)
	Optional<Student> findByid(Long id);

	@Query(value = "select * from student  where franchise_id = ?2 and first_name like CONCAT(?1, '%') and status = true", nativeQuery = true)
//	@Query(value = "SELECT * FROM student WHERE first_name LIKE CONCAT(?1, '%') AND status = true", nativeQuery = true)
	List<Student> findTheStudentUsigTheName(String name, Long franhce_id);

	@Query(value = "select * from student where franchise_id = ? and status = true", nativeQuery = true)
	List<Student> findByFranchiseId(Long franchise_id);

//	@Query(value = "select * from student where franchise_id = ? and status = true and order by student_id desc", nativeQuery = true)
//	Page<Student> findByFranchiseId(Long franchise_id, PageRequest pageRequest);

	@Query(value = "select * from student where franchise_id = ?1 and status = true order by student_id desc, creation_time desc", nativeQuery = true)
	Page<Student> findByFranchiseId(Long franchise_id, Pageable pageable);

	
	@Query(value = "SELECT COUNT(*) FROM student  WHERE franchise_id = ?", nativeQuery = true)
	long countByFranchiseId(Long franchiseId);

	@Query(value = "SELECT s FROM student s JOIN s.courses c WHERE s.franchise.franchise_id = :franchiseId AND c.course_id = :courseId", nativeQuery = true)
	List<Student> findByFranchiseIdAndCourseId(@Param("franchiseId") Long franchiseId,
			@Param("courseId") Long courseId);

	@Modifying
	@Query(value = "DELETE FROM student WHERE franchise_id = :franchiseId", nativeQuery = true)
	void deleteByFranchiseId(Long franchiseId);

	@Query(value = "select * from student where student_id = ?  and status = true", nativeQuery = true)
	Optional<Student> findbystudentUsingId(Long id);

	@Query(value = "select s.* from student s join student_course sc on s.student_id = sc.student_id where sc.course_id = ? and s.status = true", nativeQuery = true)
	List<Student> getBYstudentUsingTheCourseID(Long id);

	@Query(value = "SELECT COUNT(*) FROM student s " + "JOIN student_course sc ON s.student_id = sc.student_id "
			+ "WHERE sc.course_id = :courseId AND s.status = true", nativeQuery = true)
	long countByCourseIdAndStatus(Long courseId);

	@Modifying
	@Query(value = "DELETE FROM student_course WHERE course_id = :courseId", nativeQuery = true)
	void deleteByCourseId(Long courseId);

	@Query(value = "SELECT s.* FROM student s JOIN student_course sc ON s.student_id = sc.student_id WHERE sc.course_id = :courseId", nativeQuery = true)
	List<Student> findByCourseId(Long courseId);

	@Query(value = "SELECT s.* FROM student s " + "JOIN student_course sc ON s.student_id = sc.student_id "
			+ "WHERE sc.course_id = :courseId AND s.status = true", nativeQuery = true)
	List<Student> findByCourseIdAndStatus(Long courseId);

	@Query(value = "SELECT COUNT(*) FROM student s JOIN student_course sc ON s.student_id = sc.student_id WHERE s.franchise_id = :franchiseId AND sc.course_id = :courseId", nativeQuery = true)
	long countStudentsByFranchiseAndCourse(Long franchiseId, Long courseId);

	@Query(value = "SELECT * FROM student WHERE student_id IN :studentIds", nativeQuery = true)
	List<Student> findStudentsByIds(@Param("studentIds") List<Long> studentIds);

	@Query(value = "select * from  student where student_id = ? and status = true", nativeQuery = true)
	Optional<Student> findStudentById(Long student_id);

	@Query(value = "select count(*) from student where student_id = ?1 and current_course_id =?2 and status = true", nativeQuery = true)
	Long getCourseAssignCountBystudentAndCourseId(Long studentId, Long courseId);

	@Query(value = "SELECT * FROM student where student_id = ?", nativeQuery = true)
	Optional<Student> findLatestExamByStudentId(Long id);

	@Query(value = "select * from student where franchise_id = :franchiseId and exam_status = 1", nativeQuery = true)
	List<Student> findAllExamCompletedStudentByFranchise(Long franchiseId);

	@Query(value = "SELECT * FROM student WHERE DATE(creation_time) BETWEEN :startDate AND :endDate AND franchise_id = :franchies_id", nativeQuery = true)
	List<Student> getAllStudentUsingDateWithFranchise(@Param("startDate") String startDate,
			@Param("endDate") String endDate, @Param("franchies_id") Long franchies_id);

	@Query(value = "SELECT * FROM student WHERE DATE(creation_time) BETWEEN :startDate AND :endDate", nativeQuery = true)
	List<Student> getAllStudentUsingDateWithoutFranchise(@Param("startDate") String startDate,
			@Param("endDate") String endDate);

	@Transactional
	@Modifying
	@Query(value = "INSERT INTO student_course (student_id, course_id) VALUES (:studentId, :courseId)", nativeQuery = true)
	void insertStudentCourseMapping(@Param("studentId") Long studentId, @Param("courseId") Long courseId);

	@Query(value = "select * from student where franchise_id =?1 and current_course_id =?2 and status = true", nativeQuery = true)
	List<Student> getAllStudentByFranchiseIdAndCourseId(Long franchies_id, Long course_id);

	@Query(value = "select * from student where status = 1",nativeQuery = true)
	List<Student> findAllActive();
	
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE student SET exam_status = 2 where student_id = ?",nativeQuery = true)
	public void offlineExamCompleted(Long studentId);
}
