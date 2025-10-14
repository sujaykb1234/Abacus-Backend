package com.abacus.franchise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abacus.franchise.response.SuccessResponse;
import com.abacus.franchise.service.NotificationsService;

@RestController
@RequestMapping("abacus/v1/notifications/admin")
public class AdminNotificationController {

	@Autowired
	NotificationsService notificationsService;

	@GetMapping("/getAllNotifications")
	public SuccessResponse getAllNotification() {
		return notificationsService.getAllNotifications();
	}

	@PostMapping("/markAsViewed/{id}")
	public SuccessResponse markAsView(@PathVariable Long id) {
		return notificationsService.markAsViewed(id);
	}

}
