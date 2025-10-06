package com.abacus.franchise.service;

import org.springframework.stereotype.Service;

import com.abacus.franchise.response.SuccessResponse;

@Service
public interface NotificationsService {
	
	public SuccessResponse getAllNotifications();
	
	public SuccessResponse markAsViewed(Long notificationId);
	
	

}
