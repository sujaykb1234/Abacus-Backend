package com.abacus.franchise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.abacus.franchise.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT COUNT(s) FROM Student s WHERE s.examStatus = 'COMPLETED'")
    long countCompletedExamStudents();

    @Query("SELECT COUNT(s) FROM Student s WHERE s.examStatus = 'REATTEMPT'")
    long countReattemptStudents();
}
