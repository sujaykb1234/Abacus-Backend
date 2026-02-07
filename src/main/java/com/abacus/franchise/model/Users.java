package com.abacus.franchise.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class Users {

	@Id
	@UuidGenerator
	@JdbcTypeCode(SqlTypes.VARCHAR)
	@Column(name = "user_id", length = 36, updatable = false, nullable = false)
	private UUID userId;

    @Column(name = "franchise_name", length = 100)
    private String franchiseName;

    @Column(name = "email", nullable = false, unique = true, length = 150)
    private String email;

    @Column(name = "mobile", unique = true, length = 15)
    private String mobile;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(name = "first_name", length = 100)
    private String firstName;
    
    @Column(name = "middle_name", length = 100)
    private String middleName;

    @Column(name = "last_name", length = 100)
    private String lastName;

	@JdbcTypeCode(SqlTypes.VARCHAR)
	@Column(name = "role_id", length = 36, updatable = false, nullable = false)
    private UUID roleId;

    @Column(name = "profile_name")
    private String profileName;
    
    @Column(name = "profile_link")
    private String profileLink;

    @Column(name = "document_name")
    private String documentName;
    
    @Column(name = "document_link")
    private String documentLink;
    
	@JdbcTypeCode(SqlTypes.VARCHAR)
	@Column(name = "franchise_id", length = 36)
    private UUID franchiseId;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    @Column(name = "failed_login_attempts")
    private Integer failedLoginAttempts = 0;

    @Column(name = "account_locked_until")
    private LocalDateTime accountLockedUntil;

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

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public UUID getRoleId() {
		return roleId;
	}

	public void setRoleId(UUID roleId) {
		this.roleId = roleId;
	}

	public String getProfileLink() {
		return profileLink;
	}

	public void setProfileLink(String profileLink) {
		this.profileLink = profileLink;
	}

	public String getDocumentLink() {
		return documentLink;
	}

	public void setDocumentLink(String documentLink) {
		this.documentLink = documentLink;
	}

	public UUID getFranchiseId() {
		return franchiseId;
	}

	public void setFranchiseId(UUID franchiseId) {
		this.franchiseId = franchiseId;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public LocalDateTime getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(LocalDateTime lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Integer getFailedLoginAttempts() {
		return failedLoginAttempts;
	}

	public void setFailedLoginAttempts(Integer failedLoginAttempts) {
		this.failedLoginAttempts = failedLoginAttempts;
	}

	public LocalDateTime getAccountLockedUntil() {
		return accountLockedUntil;
	}

	public void setAccountLockedUntil(LocalDateTime accountLockedUntil) {
		this.accountLockedUntil = accountLockedUntil;
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

	public String getFranchiseName() {
		return franchiseName;
	}

	public void setFranchiseName(String franchiseName) {
		this.franchiseName = franchiseName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}


}
