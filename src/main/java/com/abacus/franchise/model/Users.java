package com.abacus.franchise.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Users {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "franchise_name", length = 100)
    private String franchiseName;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(unique = true, length = 15)
    private String mobile;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(name = "first_name", length = 100)
    private String firstName;

    @Column(name = "middle_name", length = 100)
    private String middleName;

    @Column(name = "last_name", length = 100)
    private String lastName;

    @Column(name = "role_id", nullable = false)
    private UUID roleId;

    private String profileName;
    private String profileLink;
    private String documentName;
    private String documentLink;

    private UUID franchiseId;
    private UUID addressId;

    private LocalDate dateOfBirth;

    private Boolean isActive = true;
    private Integer failedLoginAttempts = 0;

    private LocalDateTime lastLogin;
    private LocalDateTime accountLockedUntil;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private UUID createdBy;
    private UUID updatedBy;

    @PrePersist
    void onCreate() {
        createdAt = updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
