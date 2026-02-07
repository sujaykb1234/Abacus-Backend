package com.abacus.franchise.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue
    @Column(name = "address_id", length = 36)
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

    @Column(name = "state_id", nullable = false, length = 36)
    private UUID stateId;

    @Column(name = "district_id", length = 36)
    private UUID districtId;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "created_by", length = 36)
    private UUID createdBy;

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
}
