package com.abacus.franchise.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.abacus.franchise.model.StudentExam;

import jakarta.transaction.Transactional;

@Repository
public interface StudentExamRepository extends JpaRepository<StudentExam, Long> {

	@Query(value = "SELECT * FROM student_exam WHERE attempt_id = ?", nativeQuery = true)
	List<StudentExam> findByExamAttemptId(Long examId);

	@Query(value = "SELECT * FROM franchise.student_exam where attempt_id = ?", nativeQuery = true)
	List<StudentExam> findAllByAttemptId(Long examId);

	@Query(value = "SELECT * FROM student_exam e WHERE course_id = :courseId AND student_id = :studentId", nativeQuery = true)
	List<StudentExam> getExamByCourseIdAndStudentId(@Param("courseId") Long courseId,
			@Param("studentId") Long studentId);

	@Query(value = "SELECT * FROM student_exam e WHERE e.course_id = :courseId AND e.student_id = :studentId ORDER BY e.attempt_id DESC LIMIT 1", nativeQuery = true)
	Optional<StudentExam> findLatestExamByCourseIdAndStudentId(@Param("courseId") Long courseId,
			@Param("studentId") Long studentId);

	@Query(value = "select * from student_exam where attempt_id = :examId AND student_id =:studentId", nativeQuery = true)
	List<StudentExam> findExamByExamAttemptIdAndStudentId(@Param("examId") Long examId,
			@Param("studentId") Long studentId);

	@Query(value = "SELECT * FROM student_exam WHERE student_id = ? ORDER BY assigned_at DESC limit 1", nativeQuery = true)
	List<StudentExam> findLatestExamByStudentId(Long id);

	@Query(value = "select * from student_exam where student_id = ?", nativeQuery = true)
	Optional<StudentExam> findByStudent(Long student_id);

	@Query(value = "SELECT * FROM student_exam  WHERE student_id = :studentId", nativeQuery = true)
	List<StudentExam> findByStudentId(Long studentId);

	@Query(value = "select * from student_exam where student_id = ?1  and course_id = ?2 and examstatus = 'COMPLETED'", nativeQuery = true)
	Optional<StudentExam> findLatestExamByStudentAndCourse(Long studentId, Long courseId);

	@Query(value = "SELECT * FROM student_exam  WHERE student_id = :studentId", nativeQuery = true)
	List<StudentExam> findAllExamsByStudent(Long studentId);

//	@Query(value = "SELECT COUNT(*) > 0 FROM student_exam WHERE student_id = :student_id AND attempt_id = :examId", nativeQuery = true)
//	Integer existsByStudentIdAndExamAttemptId(Long student_id, Long examId);

	@Query(value = "SELECT * FROM student_exam WHERE student_id = :student_id AND attempt_id = :examId", nativeQuery = true)
	Optional<StudentExam> existsByStudentIdAndExamAttemptId(Long student_id, Long examId);
	
	@Query(value = "select * from student_exam where attempt_id = :examId and franchise_id = :franchiseId", nativeQuery = true)
	List<StudentExam> findByExamAndFranchiseId(Long examId, Long franchiseId);

	@Query(value = "SELECT * FROM student_exam WHERE student_id IN :studentIds AND attempt_id = :examId", nativeQuery = true)
	List<StudentExam> findByStudentIdsAndExamId(List<Long> studentIds, Long examId);

	@Query(value = "select * from student_exam where student_id = :studentId and attempt_id = :examId", nativeQuery = true)
	Optional<StudentExam> findByStudentIdAndExamId(Long studentId, Long examId);

	@Query(value = "SELECT * FROM student_exam WHERE reattempt_status IN ('1', '2', '3') order by id desc", nativeQuery = true)
	List<StudentExam> findAllRequestForReAttempt();

	@Query(value = "SELECT * FROM student_exam  WHERE student_id = :studentId ORDER BY assigned_at DESC", nativeQuery = true)
	Optional<StudentExam> findLastExamByStudentId(Long studentId);

	@Query(value = "SELECT * FROM student_exam where mark_as_download =false and examstatus = 2", nativeQuery = true)
	List<StudentExam> findAllExam();
	
	@Modifying
	@Transactional
	@Query(value="UPDATE student_exam SET examstatus = 2,marks = ?4 where course_id = ?2 and franchise_id = ?3 and  student_id = ?1 ",nativeQuery = true)
	public void changeExamStatus(Long studentId,Long courseId,Long franchiseId,Integer marks);
	
	@Query(value = """
		SELECT * FROM student_exam 
		WHERE mark_as_download = false AND (franchise_id = ?1 OR ?1 IS NULL) 
		AND examstatus = 2 
		AND (?2 IS NULL OR DATE(exam_submission_time) BETWEEN ?2 AND COALESCE(?3, ?2));
	""", nativeQuery = true)
	List<StudentExam> findAllExamByFranchieseDate(Long franchieseId,String startDate,String endDate);

	@Query(value = "select * from student_exam where franchise_id = ? and course_id =? and examstatus ='1' and exam_type = 1", nativeQuery = true)
	List<StudentExam> findOfflineStudentByFranchiseAndCourse(Long franchiseId, Long courseId);

	@Query(value = "select * from student_exam where attempt_id =? and student_id = ? and course_id = ? and examstatus = '2'", nativeQuery = true)
	Optional<StudentExam> findByExamStudentAndCourseId(Long attemptId, Long student_id, Long course_id);

	@Query(value = "select * from student_exam where franchise_id = ? and examstatus ='COMPLETED' and exam_type = 0", nativeQuery = true)
	List<StudentExam> findOnlineStudentByFranchise(Long franchiseId);

	@Query(value = "SELECT * FROM student_exam WHERE student_id = :studentId ORDER BY assigned_at DESC LIMIT 1", nativeQuery = true)
	StudentExam findLastAssignedExamByStudent(@Param("studentId") Long studentId);

	@Query(value = "SELECT * FROM student_exam se WHERE se.student_id = :studentId AND se.franchise_id = :franchiseId AND se.course_id = :courseId limit 1", nativeQuery = true)
	Optional<StudentExam> existsByStudentIdAndFranchiseIdAndCourseId(@Param("studentId") Long studentId,
			@Param("franchiseId") Long franchiseId, @Param("courseId") Long courseId);

	@Query(value = "SELECT * FROM student_exam e WHERE e.student_id = :studentId ORDER BY e.id DESC LIMIT 1", nativeQuery = true)
	Optional<StudentExam> findLatestExamByStudent(Long studentId);

	@Query(value = "SELECT count(*) FROM student_exam  where attempt_id = ?", nativeQuery = true)
	int existsByExamId(String valueOf);


}
