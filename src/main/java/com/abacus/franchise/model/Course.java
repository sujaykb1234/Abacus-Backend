package com.abacus.franchise.model;

import com.abacus.franchise.utility.CourseType;
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
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long courseId;

    @Column(name = "course_name", nullable = false, length = 150)
    private String courseName;

    @Column(name = "course_duration", length = 50)
    private String courseDuration;

    @Column(name = "no_of_books")
    private Integer noOfBooks;

    @Enumerated(EnumType.STRING)
    @Column(name = "course_type", nullable = false)
    private CourseType courseType; // ABACUS, OTHER

    @Column(name = "course_status")
    private boolean courseStatus = true;

    // Business methods for bidirectional relationship
    public void addStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }
        if (!this.students.contains(student)) {
            this.students.add(student);
        }
    }

    public void removeStudent(Student student) {
        if (this.students != null && this.students.contains(student)) {
            this.students.remove(student);
        }
    }
}
