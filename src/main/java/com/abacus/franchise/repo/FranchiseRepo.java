package com.abacus.franchise.repo;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.abacus.franchise.model.Franchise;

@Repository
public interface FranchiseRepo extends JpaRepository<Franchise, Long> {

	@Query(value = "select * from franchise where franchise_email = ? ", nativeQuery = true)
	Optional<Franchise> findByEmail(String frinchise_email);

	@Query(value = "select * from franchise where  mobile_no = ? ", nativeQuery = true)
	Optional<Franchise> findByusername(String frinchiesUsername);

	@Query(value = "select * from franchise where franchise_email = ? ", nativeQuery = true)
	Optional<Franchise> getbyuserName(String franchiesUsername);

	@Query(value = "select * from franchise where franchise_id= ?", nativeQuery = true)
	Optional<Franchise> getbyetheFranchiesUsingTheid(Long id);

	@Query(value = "SELECT * FROM franchise  where franchise_status = 1 ORDER BY franchise_name ASC", nativeQuery = true)
	Page<Franchise> getAllFranchies(PageRequest page);

	@Modifying
	@Query("DELETE FROM Franchise f WHERE f.id = :id")
	void deleteByIdForFranchise(Long id);

	@Query(value = "select * from franchise where franchise_status = 0", nativeQuery = true)
	List<Franchise> findAllRequests();

	@Query("SELECT f.franchise_number FROM Franchise f WHERE f.franchise_number LIKE ?1 ORDER BY f.franchise_number DESC")
	List<String> findLastFranchiseNumber(String prefix);

	@Query(value = "SELECT * FROM franchise ORDER BY franchise_number DESC LIMIT 1", nativeQuery = true)
	Optional<Franchise> findTopByOrderByFranchiseNumberDesc();

	@Query(value = "select * from franchise order by franchise_name ", nativeQuery = true)
	List<Franchise> findAllFranchise();

	@Query(value = "SELECT DISTINCT c.franchise_franchise_id FROM franchise f JOIN franchise_courses c WHERE c.courses_course_id = :courseId", nativeQuery = true)
	List<Long> findByCourseId(@Param("courseId") Long courseId);

	@Query(value = "select * from student where franchise_id = ? and status = true", nativeQuery = true)
	Optional<Franchise> findByFraId(Long franchise_id);

	@Query(value = "SELECT COUNT(*) FROM franchise_courses WHERE franchise_franchise_id = :franchiseId AND courses_course_id IN (:courseIds)", nativeQuery = true)
	int checkCourseAssignedToAnotherFranchise(Long franchiseId, Set<Long> courseIds);

	@Query(value = "select * from franchise where mobile_no = ?", nativeQuery = true)
	Optional<Franchise> findByMobile(String mobile_no);

	@Query(value = "SELECT * FROM franchise f JOIN franchise_courses fc ON f.franchise_id = fc.franchise_franchise_id WHERE f.franchise_id = :franchiesId AND (fc.courses_course_id = :courseId OR f.creation_time BETWEEN :startDate AND :endDate)", nativeQuery = true)
	List<Franchise> findDetailsUsingFidAndCid(Long franchiesId, Long courseId, String startDate, String endDate);

	@Query(value = "SELECT * FROM franchise WHERE DATE(creation_time) BETWEEN :formattedStartDateTime AND :formattedEndDateTime", nativeQuery = true)
	List<Franchise> getAllFranchiesUsingDate(String formattedStartDateTime, String formattedEndDateTime);

	@Query(value = "select franchise_franchise_id from franchise_courses where courses_course_id = ?", nativeQuery = true)
	List<Long> getAllFranchiesUsingCourseId(Long course_id);

	@Query(value = "select franchise_name from franchise where franchise_id = :franchiseId", nativeQuery = true)
	String findFranchiseName(Long franchiseId);
	
	@Query(value = "select * from franchise where mobile_no = ?", nativeQuery = true)
	List<Franchise> getFranchiseByMobileNo(String mobile);
}
