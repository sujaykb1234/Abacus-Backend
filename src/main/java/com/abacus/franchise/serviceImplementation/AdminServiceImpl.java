package com.abacus.franchise.serviceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abacus.franchise.dto.response.AdminDashboardCountDTO;
import com.abacus.franchise.interfaces.AdminService;
import com.abacus.franchise.repository.CourseRepository;
import com.abacus.franchise.repository.FranchiseKitRequestRepository;
import com.abacus.franchise.repository.FranchiseRepository;
import com.abacus.franchise.repository.ProductOrderRepository;
import com.abacus.franchise.repository.StudentRepository;
import com.abacus.franchise.response.SuccessResponse;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private FranchiseRepository franchiseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private FranchiseKitRequestRepository kitRequestRepository;

    @Autowired
    private ProductOrderRepository productOrderRepository;

    @Override
    public SuccessResponse getAdminDashboardCounts() {

        AdminDashboardCountDTO dto = new AdminDashboardCountDTO();

        dto.setTotalFranchise(franchiseRepository.count());
        dto.setTotalStudents(studentRepository.count());
        dto.setTotalCourses(courseRepository.count());

        dto.setFranchiseRequests(franchiseRepository.countPendingRequests());

        dto.setKitOrderDiff(kitRequestRepository.countDiffOrders());
        dto.setKitOrderAdditional(kitRequestRepository.countAdditionalOrders());

        dto.setExamCompletedStudents(studentRepository.countCompletedExamStudents());
        dto.setReAttemptStudents(studentRepository.countReattemptStudents());

        dto.setProductRequests(productOrderRepository.countPendingProductRequests());

        return new SuccessResponse("Dashboard counts fetched successfully", dto);
    }
}
