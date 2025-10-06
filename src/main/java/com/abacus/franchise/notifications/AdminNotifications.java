package com.abacus.franchise.notifications;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class AdminNotifications {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String message;
	private String franchise_number;
	private String franchiseName;
	private String notificationType;
	private LocalDateTime seenTime;
	private boolean isViewed = false;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

	public String getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getFranchiseName() {
		return franchiseName;
	}

	public void setFranchiseName(String franchiseName) {
		this.franchiseName = franchiseName;
	}

	public LocalDateTime getSeenTime() {
		return seenTime;
	}

	public void setSeenTime(LocalDateTime seenTime) {
		this.seenTime = seenTime;
	}

	public boolean isViewed() {
		return isViewed;
	}

	public void setViewed(boolean isViewed) {
		this.isViewed = isViewed;
	}


	public AdminNotifications(Long id, String message, String franchise_number, String franchiseName,
			String notificationType, LocalDateTime seenTime, boolean isViewed) {
		super();
		this.id = id;
		this.message = message;
		this.franchise_number = franchise_number;
		this.franchiseName = franchiseName;
		this.notificationType = notificationType;
		this.seenTime = seenTime;
		this.isViewed = isViewed;
	}

	public String getFranchise_number() {
		return franchise_number;
	}

	public void setFranchise_number(String franchise_number) {
		this.franchise_number = franchise_number;
	}

	public AdminNotifications() {
		super();
		// TODO Auto-generated constructor stub
	}

}
