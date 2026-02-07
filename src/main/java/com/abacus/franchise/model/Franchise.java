package com.abacus.franchise.model;

import java.util.HashSet;
import java.util.Set;

import com.abacus.franchise.utility.FranchiseStatus;
import com.abacus.franchise.utility.Roles;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "franchise")
public class Franchise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "franchise_id")
    private Long franchiseId;

    @Column(name = "franchise_number")
    private String franchiseNumber;

    @Column(name = "franchise_name")
    private String franchiseName;

    @Column(name = "franchise_owner")
    private String franchiseOwner;

    @Column(name = "mobile_no")
    private String mobileNo;

    private String gender;

    @Column(name = "owner_dob")
    private String ownerDOB;

    @Column(name = "franchise_email")
    private String franchiseEmail;

    @Column(name = "franchise_password")
    private String franchisePassword;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address franchiseAddress;

    @Column(name = "profile_image_name")
    private String profileImageName;

    @Column(name = "profile_image_link")
    private String profileImageLink;

    @Column(name = "document_type")
    private String documentType;

    @Column(name = "document_number")
    private String documentNumber;

    @Column(name = "document_image_link")
    private String documentImageLink;

    @Column(name = "document_image_name")
    private String documentImageName;

    @Column(name = "creation_time")
    private String creationTime;

    @Column(name = "modification_time")
    private String modificationTime;

    @Enumerated(EnumType.STRING)
    private FranchiseStatus franchiseStatus = FranchiseStatus.PENDING;

    @Enumerated(EnumType.STRING)
    private Roles roles;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "franchise_course",
            joinColumns = @JoinColumn(name = "franchise_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Course> courses = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "franchise_exam_attempt",
            joinColumns = @JoinColumn(name = "franchise_id"),
            inverseJoinColumns = @JoinColumn(name = "attempt_id")
    )
    private Set<ExamAttempt> examAttempts = new HashSet<>();

    // ---------- Helper methods (logic preserved) ----------

    public void addCourse(Course course) {
        this.courses.add(course);
    }

    public void removeCourse(Course course) {
        this.courses.remove(course);
    }

    public Franchise(
            Long franchiseId,
            String franchiseNumber,
            String franchiseName,
            String franchiseOwner,
            String mobileNo,
            String gender,
            String ownerDOB,
            String franchiseEmail,
            String franchisePassword,
            Address franchiseAddress,
            String profileImageName,
            String profileImageLink,
            String documentType,
            String documentNumber,
            String documentImageLink,
            String documentImageName,
            String creationTime,
            String modificationTime,
            FranchiseStatus franchiseStatus,
            Set<Course> courses,
            Set<ExamAttempt> examAttempts
    ) {
        this.franchiseId = franchiseId;
        this.franchiseNumber = franchiseNumber;
        this.franchiseName = franchiseName;
        this.franchiseOwner = franchiseOwner;
        this.mobileNo = mobileNo;
        this.gender = gender;
        this.ownerDOB = ownerDOB;
        this.franchiseEmail = franchiseEmail;
        this.franchisePassword = franchisePassword;
        this.franchiseAddress = franchiseAddress;
        this.profileImageName = profileImageName;
        this.profileImageLink = profileImageLink;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.documentImageLink = documentImageLink;
        this.documentImageName = documentImageName;
        this.creationTime = creationTime;
        this.modificationTime = modificationTime;
        this.franchiseStatus = franchiseStatus;
        this.courses = courses;
        this.examAttempts = examAttempts;
    }
}
