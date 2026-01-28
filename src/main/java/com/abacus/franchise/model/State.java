package com.abacus.franchise.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "state")
public class State {
	    @Id
	    @GeneratedValue(strategy = GenerationType.UUID)
	    @Column(name = "state_id", nullable = false, updatable = false,columnDefinition = "CHAR(36)")
	    private UUID stateId;

	    @Column(name = "state_name", nullable = false, length = 150)
	    private String stateName;

	    @Column(name = "is_active")
	    private Boolean isActive = true;

	    @Column(name = "created_at", updatable = false)
	    private LocalDateTime createdAt;

	    @Column(name = "updated_at")
	    private LocalDateTime updatedAt;

	    @Column(name = "created_by",columnDefinition = "CHAR(36)")
	    private UUID createdBy;

	    @Column(name = "updated_by",columnDefinition = "CHAR(36)")
	    private UUID updatedBy;

	    @PrePersist
	    protected void onCreate() {
	        this.createdAt = LocalDateTime.now();
	        this.updatedAt = LocalDateTime.now();
	    }

	    @PreUpdate
	    protected void onUpdate() {
	        this.updatedAt = LocalDateTime.now();
	    }

		public UUID getStateId() {
			return stateId;
		}

		public void setStateId(UUID stateId) {
			this.stateId = stateId;
		}

		public String getStateName() {
			return stateName;
		}

		public void setStateName(String stateName) {
			this.stateName = stateName;
		}

		public Boolean getIsActive() {
			return isActive;
		}

		public void setIsActive(Boolean isActive) {
			this.isActive = isActive;
		}

		public LocalDateTime getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
		}

		public LocalDateTime getUpdatedAt() {
			return updatedAt;
		}

		public void setUpdatedAt(LocalDateTime updatedAt) {
			this.updatedAt = updatedAt;
		}

		public UUID getCreatedBy() {
			return createdBy;
		}

		public void setCreatedBy(UUID createdBy) {
			this.createdBy = createdBy;
		}

		public UUID getUpdatedBy() {
			return updatedBy;
		}

		public void setUpdatedBy(UUID updatedBy) {
			this.updatedBy = updatedBy;
		}
	
}
