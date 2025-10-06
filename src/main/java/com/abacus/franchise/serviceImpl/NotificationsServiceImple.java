package com.abacus.franchise.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.abacus.franchise.model.Franchise;
import com.abacus.franchise.notifications.AdminNotifications;
import com.abacus.franchise.repo.AdminNotificationRepo;
import com.abacus.franchise.response.SuccessResponse;
import com.abacus.franchise.service.NotificationsService;

@Service
public class NotificationsServiceImple implements NotificationsService {

	@Autowired
	AdminNotificationRepo adminNotificationRepo;

	@Override
	public SuccessResponse getAllNotifications() {
		SuccessResponse response = new SuccessResponse();
		List<AdminNotifications> findAll = adminNotificationRepo.findAll();
		if (findAll.isEmpty()) {
			response.notificationNotFound();
			return response;
		}
		response.notificationsRetrived(findAll);
		return response;
	}

	@Override
	public SuccessResponse markAsViewed(Long notificationId) {
		SuccessResponse response = new SuccessResponse();
		if (notificationId == null) {
			response.nullData();
			return response;
		}
		Optional<AdminNotifications> findById = adminNotificationRepo.findById(notificationId);
		if (!findById.isPresent()) {
			response.notificationNotFound();
			return response;
		}
		AdminNotifications adminNotifications = findById.get();
		adminNotifications.setSeenTime(LocalDateTime.now());
		adminNotifications.setViewed(true);
		AdminNotifications save = adminNotificationRepo.save(adminNotifications);
		response.viewed(save);
		return response;
	}

	public void generateNotificationAfterAddFranchise(Franchise franchise) {
		AdminNotifications adminNotifications = new AdminNotifications();
		adminNotifications.setFranchise_number(franchise.getFranchise_number());
		adminNotifications.setFranchiseName(franchise.getFranchise_name());
		adminNotifications.setNotificationType("FRANCHISE-REQUEST");
		adminNotifications.setMessage("New Franchise added please review..");
		adminNotificationRepo.save(adminNotifications);
	}

	public void generateNotificationAfterFranchiseKitRequest(Franchise franchise, String courseName) {
		AdminNotifications adminNotifications = new AdminNotifications();
		adminNotifications.setFranchise_number(franchise.getFranchise_number());
		adminNotifications.setFranchiseName(franchise.getFranchise_name());
		adminNotifications.setNotificationType("KIT-REQUEST");
		adminNotifications.setMessage("Franchise kit request for the course " + courseName + "please check..");
		adminNotificationRepo.save(adminNotifications);
	}

	@Scheduled(fixedDelay = 300000)
	public void deleteOldNotifications() {
		LocalDateTime minusMinutes = LocalDateTime.now().minusMinutes(5);
		List<AdminNotifications> findExpiredNofifications = adminNotificationRepo
				.findExpiredNofifications(minusMinutes);
		adminNotificationRepo.deleteAll(findExpiredNofifications);
	}

}
