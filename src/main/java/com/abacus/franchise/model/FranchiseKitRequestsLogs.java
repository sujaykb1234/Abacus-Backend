package com.abacus.franchise.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "franchise_kit_request_logs")
public class FranchiseKitRequestsLogs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "franchise_id", nullable = false)
    private Long franchiseId;

    @Column(name = "franchise_name", nullable = false)
    private String franchiseName;

    @Column(name = "franchise_number", nullable = false)
    private String franchiseNumber;

    @Column(name = "course_id", nullable = false)
    private Long courseId;

    @Column(name = "course_name", nullable = false)
    private String courseName;

    @Column(name = "ordered_kits", nullable = false)
    private Integer orderedKits;

    @Column(name = "franchise_mobile")
    private String franchiseMobile;

    @Column(name = "requested_date", nullable = false)
    private String requestedDate;

    public FranchiseKitRequestsLogs(
            Long franchiseId,
            String franchiseName,
            String franchiseNumber,
            Long courseId,
            String courseName,
            Integer orderedKits,
            String franchiseMobile,
            String requestedDate
    ) {
        this.franchiseId = franchiseId;
        this.franchiseName = franchiseName;
        this.franchiseNumber = franchiseNumber;
        this.courseId = courseId;
        this.courseName = courseName;
        this.orderedKits = orderedKits;
        this.franchiseMobile = franchiseMobile;
        this.requestedDate = requestedDate;
    }
}
