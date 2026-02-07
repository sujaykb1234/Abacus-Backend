package com.abacus.franchise.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import com.abacus.franchise.utility.RequestStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "kit_requests")
public class KitRequests {

   @Id
   @UuidGenerator
   @JdbcTypeCode(SqlTypes.VARCHAR)
   @Column(name = "kit_request_id", length = 36, updatable = false, nullable = false)
   private UUID kitRequestId;
	
   @JdbcTypeCode(SqlTypes.VARCHAR)
   @Column(name = "franchise_id", length = 36, updatable = false, nullable = false)
   private UUID franchiseId;
   
   @Column(name = "request_status", nullable = false)
   private RequestStatus requestStatus = RequestStatus.PLACED;
   
   @JdbcTypeCode(SqlTypes.VARCHAR)
   @Column(name = "address_id", length = 36, updatable = false, nullable = false)
   private UUID addressId;
   
   @Column(name = "placed_date")
   private LocalDateTime placedDate;
   
   @Column(name = "dispatched_date")
   private LocalDateTime dispatchedDate;
   
   @Column(name = "delivered_date")
   private LocalDateTime deliveredDate;
   
   @Column(name = "is_active")
   private Boolean isActive = true;

   @Column(name = "created_at", updatable = false)
   private LocalDateTime createdAt;

   @Column(name = "updated_at")
   private LocalDateTime updatedAt;

	@JdbcTypeCode(SqlTypes.VARCHAR)
	@Column(name = "created_by", length = 36)
   private UUID createdBy;

	@JdbcTypeCode(SqlTypes.VARCHAR)
	@Column(name = "updated_by", length = 36)
   private UUID updatedBy;
	
	
	@PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.placedDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

	public UUID getKitRequestId() {
		return kitRequestId;
	}

	public void setKitRequestId(UUID kitRequestId) {
		this.kitRequestId = kitRequestId;
	}

	public UUID getFranchiseId() {
		return franchiseId;
	}

	public void setFranchiseId(UUID franchiseId) {
		this.franchiseId = franchiseId;
	}

	public RequestStatus getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(RequestStatus requestStatus) {
		this.requestStatus = requestStatus;
	}

	public UUID getAddressId() {
		return addressId;
	}

	public void setAddressId(UUID addressId) {
		this.addressId = addressId;
	}

	public LocalDateTime getPlacedDate() {
		return placedDate;
	}

	public void setPlacedDate(LocalDateTime placedDate) {
		this.placedDate = placedDate;
	}

	public LocalDateTime getDispatchedDate() {
		return dispatchedDate;
	}

	public void setDispatchedDate(LocalDateTime dispatchedDate) {
		this.dispatchedDate = dispatchedDate;
	}

	public LocalDateTime getDeliveredDate() {
		return deliveredDate;
	}

	public void setDeliveredDate(LocalDateTime deliveredDate) {
		this.deliveredDate = deliveredDate;
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
