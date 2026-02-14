package com.abacus.franchise.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "franchise_kit_request")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FranchiseKitRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "avelable_total_kits")
    private Integer avelableTotalKits;

    @Column(name = "course_id")
    private Long courseId;

    @Column(name = "course_name", length = 255)
    private String courseName;

    @Column(name = "franchise_mobile", length = 255)
    private String franchiseMobile;

    @Column(name = "franchise_id")
    private Long franchiseId;

    @Column(name = "franchise_name", length = 255)
    private String franchiseName;

    @Column(name = "franchise_number", length = 255)
    private String franchiseNumber;

    @Column(name = "franchise_owner", length = 255)
    private String franchiseOwner;

    @Column(name = "kit_order_status")
    private Byte kitOrderStatus;

    @Column(name = "ordered_kits")
    private Integer orderedKits;

    @Column(name = "provided_kits_count_to_franchise")
    private Integer providedKitsCountToFranchise;

    @Column(name = "remaining_kits_send_to_franchise")
    private Integer remainingKitsSendToFranchise;

    @Column(name = "remaining_students")
    private Integer remainingStudents;

    @Column(name = "requested_date", length = 255)
    private String requestedDate;

    @Column(name = "tracking_number", length = 255)
    private String trackingNumber;

    // Optional: Relationship to Franchise (if needed)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "franchise_id", insertable = false, updatable = false)
    private Franchise franchise;
}
