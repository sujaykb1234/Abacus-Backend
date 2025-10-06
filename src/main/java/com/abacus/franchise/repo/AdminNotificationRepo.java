package com.abacus.franchise.repo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.abacus.franchise.notifications.AdminNotifications;

@Repository
public interface AdminNotificationRepo extends JpaRepository<AdminNotifications, Long> {

	@Query(value = "select * from admin_notifications where seen_time < :time and is_viewed = true", nativeQuery = true)
	List<AdminNotifications> findExpiredNofifications(LocalDateTime time);

}
