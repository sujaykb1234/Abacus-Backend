package com.abacus.franchise.repo;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.abacus.franchise.dto.UserAddressDetail;
import com.abacus.franchise.dto.UserDetail;
import com.abacus.franchise.model.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, UUID>{

	@Query(value="SELECT user_id FROM users WHERE user_id = :userId AND role_id IN ('550e8400-e29b-41d4-a716-446655440002','550e8400-e29b-41d4-a716-446655440003') AND is_active = true",nativeQuery = true)
	UUID checkFranchiseIdIsExistOrNot(String userId);

	
	@Query(value="SELECT password_hash,is_active FROM users WHERE mobile = :username",nativeQuery = true)
	UserDetail checkMobileNoIsPresentOrNot(String username);

	@Query(value="""
	SELECT u.user_id,
		       u.date_of_birth,
			   u.email,
			   u.first_name,
		       u.franchise_name,
		       u.last_name,
		       u.middle_name,
		       u.mobile,
		       u.profile_link,
		       u.document_link,
		       a.line1,
		       a.city,
		       a.landmark,
		       a.country_name,
		       a.pincode,
		       s.state_name,
		       d.district_name
		FROM users u 
		JOIN address a on a.address_id = u.address_id AND a.is_active = true
		JOIN state s on s.state_id = a.state_id AND s.is_active = true
		JOIN district d on d.district_id = a.district_id AND d.is_active = true
		JOIN roles r on r.role_id = u.role_id AND r.is_active = true
		WHERE u.user_id = :userId  AND r.role_name = :roleName  AND u.is_active = TRUE ;			
	""",nativeQuery = true)
	UserAddressDetail getUserAddressDetailByUserId(@Param("userId") String userId,@Param("roleName") String roleName);
	
	@Query(value="""
	SELECT u.user_id,
		       u.date_of_birth,
			   u.email,
			   u.first_name,
		       u.franchise_name,
		       u.last_name,
		       u.middle_name,
		       u.mobile,
		       u.profile_link,
		       u.document_link,
		       a.line1,
		       a.city,
		       a.landmark,
		       a.country_name,
		       a.pincode,
		       s.state_name,
		       d.district_name
		FROM users u 
		JOIN address a on a.address_id = u.address_id AND a.is_active = true
		JOIN state s on s.state_id = a.state_id AND s.is_active = true
		JOIN district d on d.district_id = a.district_id AND d.is_active = true
		JOIN roles r on r.role_id = u.role_id AND r.is_active = true
		WHERE u.franchise_id = :franchiseId AND u.is_active = TRUE ;			
	""",nativeQuery = true)
	List<UserAddressDetail> getStudentDetailByFranchiseId(@Param("franchiseId") String franchiseId);

}
