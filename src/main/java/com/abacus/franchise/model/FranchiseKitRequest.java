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
@Table(name = "franchise_kit_request")
public class FranchiseKitRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "franchise_name")
    private String franchiseName;

    @Column(name = "franchise_number")
    private String franchiseNumber;

    @Column(name = "franchise_owner")
    private String franchiseOwner;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "remaining_students")
    private Integer remainingStudents = 0;

    @Column(name = "ordered_kits")
    private Integer orderedKits = 0;

    @Column(name = "franchise_mobile")
    private String franchiseMobile;

    @Column(name = "provided_kits_count")
    private Integer providedKitsCountToFranchise = 0;

    @Column(name = "available_total_kits")
    private Integer availableTotalKits = 0;

    @Column(name = "remaining_kits_to_send")
    private Integer remainingKitsSendToFranchise = 0;

    @Column(name = "tracking_number")
    private String trackingNumber;

    @Column(name = "course_id")
    private Long courseId;

    @Column(name = "franchise_id")
    private Long franchiseId;

    @Column(name = "requested_date")
    private String requestedDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "kit_order_status")
    private KitOrderStatus kitOrderStatus = KitOrderStatus.PENDING;

    public FranchiseKitRequest(
            Long id,
            String franchiseName,
            String franchiseNumber,
            String courseName,
            Integer remainingStudents,
            Integer orderedKits,
            String franchiseMobile,
            Integer providedKitsCountToFranchise,
            Integer availableTotalKits,
            Integer remainingKitsSendToFranchise,
            String trackingNumber,
            Long courseId,
            Long franchiseId,
            String requestedDate,
            KitOrderStatus kitOrderStatus
    ) {
        this.id = id;
        this.franchiseName = franchiseName;
        this.franchiseNumber = franchiseNumber;
        this.courseName = courseName;
        this.remainingStudents = remainingStudents;
        this.orderedKits = orderedKits;
        this.franchiseMobile = franchiseMobile;
        this.providedKitsCountToFranchise = providedKitsCountToFranchise;
        this.availableTotalKits = availableTotalKits;
        this.remainingKitsSendToFranchise = remainingKitsSendToFranchise;
        this.trackingNumber = trackingNumber;
        this.courseId = courseId;
        this.franchiseId = franchiseId;
        this.requestedDate = requestedDate;
        this.kitOrderStatus = kitOrderStatus;
    }
}
