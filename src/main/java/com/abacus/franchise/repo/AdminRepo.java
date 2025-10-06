package com.abacus.franchise.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.abacus.franchise.model.Admin;

@Repository
public interface AdminRepo  extends JpaRepository<Admin, Long>{

	
	@Query(value = "select * from admin where admin_mobile_no =  ?", nativeQuery = true)
	Optional<Admin> findByusername(String adminUsername);

	@Query(value = "select * from admin where admin_mobile_no= ?",nativeQuery = true)
	Optional<Admin> findByMobileNo(String admin_mobileNo);

	@Query(value = "select * from admin where admin_email_id = ?", nativeQuery =  true)
	Optional<Admin> findByEmailId(String admin_emailId);

	@Query(value = "select * from admin where admin_email_id = ?", nativeQuery =  true)
	Optional<Admin> findByEmail(String mailTo);

	@Query(value = "select * from admin where admin_email_id = ?", nativeQuery =  true)
	Optional<Admin> findByusernameForPassword(String loginusername);
	
	@Query(value = "SELECT * FROM admin WHERE admin_id = 1",nativeQuery = true)
	Optional<Admin> getAdminDetail();

}
