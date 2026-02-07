package com.abacus.franchise.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "certificate")
public class Certificate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "certificate_id")
    private Long certificateId;

    @Column(name = "certificate_number", length = 100, nullable = false)
    private String certificateNumber;

    @Column(name = "student_marks")
    private Integer studentMarks;

    @Column(name = "exam_name", length = 100)
    private String examName;

    @Column(name = "student_name", length = 150)
    private String studentName;

    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "course_name", length = 150)
    private String courseName;

    @Column(name = "admission_date", length = 30)
    private String admissionDate;

    @Column(name = "course_id")
    private Long courseId;

    @Column(name = "completion_date", length = 30)
    private String completionDate;

    @Column(name = "student_profile_img_link", length = 500)
    private String studentProfImgLink;

    @Column(name = "is_generated")
    private boolean isGenerated = false;

    @Column(name = "status")
    private Boolean status = true;
}
