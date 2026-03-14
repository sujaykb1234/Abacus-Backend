package com.abacus.franchise.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminDashboardCountDTO {

    private Long totalFranchise;
    private Long totalStudents;
    private Long totalCourses;
    private Long franchiseRequests;
    private Long kitOrderDiff;
    private Long kitOrderAdditional;
    private Long examCompletedStudents;
    private Long reAttemptStudents;
    private Long productRequests;
}
