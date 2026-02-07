package com.abacus.franchise.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Setter;

@Entity
public class PracticeStudent {

    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    @Setter
    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String mobileNo;

    @Setter
    @Column(nullable = false, unique = true)
    private String email;

    @Setter
    @Column(nullable = false)
    private String password;

    private String gender;

    @Setter
    private LocalDate dob;

    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    private String profileImageName;
    private String profileImageLink;
    @Setter
    private String documentImageLink;
    private String documentImageName;

    private LocalDateTime creationTime;
    private LocalDateTime modificationTime;

    @Setter
    private Boolean status = true;

    // ======================
    // JPA CALLBACKS
    // ======================

    @PrePersist
    public void onCreate() {
        this.creationTime = LocalDateTime.now();
        this.modificationTime = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.modificationTime = LocalDateTime.now();
    }

    // ======================
    // GETTERS & SETTERS
    // ======================

    public Long getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public String getEmail() {
        return email;
    }

    // ⚠️ Never expose password in toString / response
    public String getPassword() {
        return password;
    }

    public String getGender() {
        return gender;
    }

    public LocalDate getDob() {
        return dob;
    }

    public Address getAddress() {
        return address;
    }

    public String getProfileImageName() {
        return profileImageName;
    }

    public String getProfileImageLink() {
        return profileImageLink;
    }

    public String getDocumentImageLink() {
        return documentImageLink;
    }

    public String getDocumentImageName() {
        return documentImageName;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public LocalDateTime getModificationTime() {
        return modificationTime;
    }

    public Boolean getStatus() {
        return status;
    }
}
