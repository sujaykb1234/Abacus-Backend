package com.abacus.franchise.repo;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.abacus.franchise.dto.UserAddressDetail;
import com.abacus.franchise.dto.UserDetail;
import com.abacus.franchise.model.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, UUID>{

	@Query(value="SELECT user_id FROM users WHERE user_id = :userId AND is_active = true",nativeQuery = true)
	UUID checkFranchiseIdIsExistOrNot(String userId);

	
	@Query(value="SELECT password_hash,is_active FROM users WHERE mobile = :username",nativeQuery = true)
	UserDetail checkMobileNoIsPresentOrNot(String username);

	@Query(value = "SELECT * FROM users",nativeQuery = true)
	List<Users> getAllList();

//	UserAddressDetail getUserById(String userId);
	
}
