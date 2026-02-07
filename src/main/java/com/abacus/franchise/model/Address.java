package com.abacus.franchise.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "address")
public class Address {

	@Id
	@UuidGenerator
	@JdbcTypeCode(SqlTypes.VARCHAR)
	@Column(name = "address_id", length = 36, updatable = false, nullable = false)
    private UUID addressId;

    @Column(name = "line1", nullable = false, length = 255)
    private String line1;

    @Column(name = "landmark", length = 200)
    private String landmark;

    @Column(name = "pincode", length = 10)
    private String pincode;

    @Column(name = "city", length = 100)
    private String city;

    @Column(name = "country_name", nullable = false)
    private String countryName = "INDIA";

	@JdbcTypeCode(SqlTypes.VARCHAR)
	@Column(name = "state_id", length = 36, updatable = false, nullable = false)
    private UUID stateId;

	@JdbcTypeCode(SqlTypes.VARCHAR)
	@Column(name = "district_id", length = 36, updatable = false, nullable = false)
    private UUID districtId;
	
	@JdbcTypeCode(SqlTypes.VARCHAR)
	@Column(name = "user_id", length = 36)
    private UUID user_id;

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
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

	public UUID getAddressId() {
		return addressId;
	}

	public void setAddressId(UUID addressId) {
		this.addressId = addressId;
	}

	public String getLine1() {
		return line1;
	}

	public void setLine1(String line1) {
		this.line1 = line1;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public UUID getStateId() {
		return stateId;
	}

	public void setStateId(UUID stateId) {
		this.stateId = stateId;
	}

	public UUID getDistrictId() {
		return districtId;
	}

	public void setDistrictId(UUID districtId) {
		this.districtId = districtId;
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

	public UUID getUser_id() {
		return user_id;
	}

	public void setUser_id(UUID user_id) {
		this.user_id = user_id;
	}
	
	
	
    
}
