package com.abacus.franchise.serviceImpl;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abacus.franchise.dto.EnrollmentDTO;
import com.abacus.franchise.model.Course;
import com.abacus.franchise.model.Student;
import com.abacus.franchise.model.StudentEnrollment;
import com.abacus.franchise.repo.CourseRepo;
import com.abacus.franchise.repo.StudentEnrollmentRepo;
import com.abacus.franchise.repo.StudentRepo;
import com.abacus.franchise.response.SuccessResponse;
import com.abacus.franchise.service.EnrollmentService;

@Service
public class EnrollmentServiceImple implements EnrollmentService {

    @Autowired
    private StudentEnrollmentRepo enrollmentRepository;

    @Autowired
    private CourseRepo courseRepository;

    @Autowired
    private StudentRepo studentRepository;


    @Override
    public SuccessResponse getEnrolledCourses(Long studentId) {
        SuccessResponse response = new SuccessResponse();

        if (studentId == null) {
            response.nullData();
            return response;
        }

        Optional<Student> student = studentRepository.findById(studentId);
        if (!student.isPresent()) {
            response.studentNotFound();
            return response;
        }

        List<StudentEnrollment> enrollments = enrollmentRepository.findByStudentId(studentId);
        if (enrollments.isEmpty()) {
            response.noStudentsFound();
            return response;
        }

        // Convert each enrollment to StudentEnrollmentDTO
        List<EnrollmentDTO> enrollmentDTOs = enrollments.stream().map(enrollment -> {
            EnrollmentDTO dto = new EnrollmentDTO();
            dto.setId(enrollment.getId());
            dto.setStudentId(enrollment.getStudent().getStudent_id());
            dto.setCourseId(enrollment.getCourse().getCourse_id());
            dto.setFranchiseId(enrollment.getFranchise().getFranchise_id());
            dto.setEnrollmentDate(enrollment.getEnrollmentDate());
            dto.setSwitchDate(enrollment.getSwitchDate());
            dto.setActive(enrollment.isActive());
            dto.setEnrollmentStatus(String.valueOf(enrollment.getEnrollmentStatus()));
            return dto;
        }).toList();

        response.studentsgetSucccesfully(enrollmentDTOs);
        return response;
    }

    public SuccessResponse markCourseAsCompleted(Long studentId, Long courseId) {
        SuccessResponse response = new SuccessResponse();
        StudentEnrollment enrollment = enrollmentRepository
                .findByStudentIdAndCourseIdAndEnrollmentStatus(studentId, courseId, "INPROGRESS").orElseThrow();

        enrollment.setEnrollmentStatus("COMPLETED");
        enrollment.setSwitchDate(LocalDateTime.now());
        enrollment.setActive(false);
        enrollmentRepository.save(enrollment);
        response.swichCourseSuccessfully(enrollment);
        return response;
    }

    public SuccessResponse enrollInNewCourse(Long studentId, Long courseId) {
        SuccessResponse response = new SuccessResponse();
        List<StudentEnrollment> activeCourses = enrollmentRepository.findByStudentIdAndEnrollmentStatus(studentId,
                "INPROGRESS");

        if (!activeCourses.isEmpty()) {
            throw new IllegalStateException("The student already has an active course in progress.");
        }

        Optional<Student> findById = studentRepository.findById(studentId);
        if (!findById.isPresent()) {
            response.studentNotFound();
            return response;
        }

        Optional<Course> findById2 = courseRepository.findById(courseId);
        if (findById2.isPresent()) {
            response.courseNotFound();
            return response;
        }
        Course course = findById2.get();
        Student student = findById.get();

        StudentEnrollment newEnrollment = new StudentEnrollment();
        newEnrollment.setStudent(student);
        newEnrollment.setCourse(course);
        newEnrollment.setEnrollmentDate(LocalDateTime.now());
        newEnrollment.setEnrollmentStatus("INPROGRESS");
        newEnrollment.setActive(true);
        enrollmentRepository.save(newEnrollment);
        response.swichCourseSuccessfully(newEnrollment);
        return response;
    }

    public List<StudentEnrollment> getCompletedAndInProgressCourses(Long studentId) {
        return enrollmentRepository.findByStudentIdAndEnrollmentStatusIn(studentId,
                Arrays.asList("COMPLETED", "INPROGRESS"));
    }

}
