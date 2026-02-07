package com.abacus.franchise.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "district")
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(
            name = "district_id",
            nullable = false,
            updatable = false,
            columnDefinition = "CHAR(36)"
    )
    private UUID districtId;

    @Column(name = "district_name", nullable = false, length = 150)
    private String districtName;

    @Column(name = "state_id", nullable = false, columnDefinition = "CHAR(36)")
    private UUID stateId;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "created_by", columnDefinition = "CHAR(36)")
    private UUID createdBy;

    @Column(name = "updated_by", columnDefinition = "CHAR(36)")
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
