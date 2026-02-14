package com.abacus.franchise.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "student")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "board", length = 255)
    private String board;

    @Column(name = "creation_time", length = 255)
    private String creationTime;

    @Column(name = "current_course_id")
    private Long currentCourseId;

    @Column(name = "current_course_name", length = 255)
    private String currentCourseName;

    @Column(name = "current_exam_id")
    private Long currentExamId;

    @Column(name = "dob", length = 255)
    private String dob;

    @Column(name = "education", length = 255)
    private String education;

    @Column(name = "email", length = 255)
    private String email;

    @Column(name = "exam_status")
    private Byte examStatus;

    @Column(name = "exam_type")
    private Byte examType;

    @Column(name = "exam_password", length = 255)
    private String examPassword;

    @Column(name = "first_name", length = 255)
    private String firstName;

    @Column(name = "gender", length = 255)
    private String gender;

    @Column(name = "last_name", length = 255)
    private String lastName;

    @Column(name = "mobile_no", length = 255)
    private String mobileNo;

    @Column(name = "modification_time", length = 255)
    private String modificationTime;

    @Column(name = "name_on_certificate", length = 255)
    private String nameOnCertificate;

    @Column(name = "password", length = 255)
    private String password;

    @Column(name = "profile_image_link", length = 255)
    private String profileImageLink;

    @Column(name = "profile_image_name", length = 255)
    private String profileImageName;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "address_address_id", length = 36, unique = true)
    private String addressAddressId;

    @Column(name = "franchise_id")
    private Long franchiseId;

    // Relationships
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "franchise_id", insertable = false, updatable = false)
    private Franchise franchise;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_address_id", insertable = false, updatable = false)
    private Address address;
}
