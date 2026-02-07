package com.abacus.franchise.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "admin")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private Long adminId;

    @Column(name = "admin_username", nullable = false, unique = true, length = 100)
    private String adminUsername;

    @Column(name = "admin_password_hash", nullable = false)
    private String passwordHash;

    @Column(name = "admin_mobile_no", length = 15, unique = true)
    private String adminMobileNo;

    @Column(name = "admin_email_id", length = 150, unique = true)
    private String adminEmailId;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Optional / infra-related (consider moving later)
    @Column(name = "access_token")
    private String accessToken;

    @Column(name = "instance_id")
    private String instanceId;

    @Column(name = "api_url")
    private String apiUrl;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
