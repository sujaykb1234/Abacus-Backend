package com.abacus.franchise.model;

import com.abacus.franchise.utility.KitOrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "franchise_different_add_kit_req")
public class FranchiseDiffrentAddKitReq {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_name")
    private String studentName;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "franchise_id")
    private Long franchiseId;

    @Column(name = "franchise_name")
    private String franchiseName;

    @Column(name = "franchise_owner")
    private String franchiseOwner;

    @Column(name = "franchise_mobile")
    private String franchiseMobile;

    @Column(name = "tracking_number")
    private String trackingNumber;

    @Column(name = "address", columnDefinition = "TEXT")
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(name = "kit_order_status")
    private KitOrderStatus kitOrderStatus = KitOrderStatus.PENDING;

    @Column(name = "course_id")
    private Long courseId;

    public FranchiseDiffrentAddKitReq(
            Long id,
            String studentName,
            String courseName,
            Long studentId,
            Long franchiseId,
            String franchiseName,
            String franchiseMobile,
            String trackingNumber,
            String address,
            KitOrderStatus kitOrderStatus,
            Long courseId
    ) {
        this.id = id;
        this.studentName = studentName;
        this.courseName = courseName;
        this.studentId = studentId;
        this.franchiseId = franchiseId;
        this.franchiseName = franchiseName;
        this.franchiseMobile = franchiseMobile;
        this.trackingNumber = trackingNumber;
        this.address = address;
        this.kitOrderStatus = kitOrderStatus;
        this.courseId = courseId;
    }
}
