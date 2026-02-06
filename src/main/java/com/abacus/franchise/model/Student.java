package com.abacus.franchise.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.abacus.franchise.utility.ExamStatus;
import com.abacus.franchise.utility.ExamType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "mobile_no", unique = true, nullable = false)
    private String mobileNo;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name_on_certificate")
    private String nameOnCertificate;

    @Column(name = "gender")
    private String gender;

    @Column(name = "dob")
    private String dob;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "current_exam_id")
    private Long currentExamId;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "student_course",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses = new ArrayList<>();

    @Column(name = "profile_image_name")
    private String profileImageName;

    @Column(name = "profile_image_link")
    private String profileImageLink;

    @Column(name = "education")
    private String education;

    @Column(name = "board")
    private String board;

    @Column(name = "exam_password")
    private String examPassword;

    @Column(name = "current_course_id")
    private Long currentCourseId;

    @Column(name = "current_course_name")
    private String currentCourseName;

    @Enumerated(EnumType.STRING)
    @Column(name = "exam_status")
    private ExamStatus examStatus = ExamStatus.PENDING;

    @Column(name = "status")
    private boolean isActive = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "franchise_id")
    private Franchise franchise;

    @Enumerated(EnumType.STRING)
    @Column(name = "exam_type")
    private ExamType examType;

    @JsonIgnore
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<StudentExam> studentExams = new ArrayList<>();

    // Audit fields
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "created_by", columnDefinition = "CHAR(36)")
    private UUID createdBy;

    @Column(name = "updated_by", columnDefinition = "CHAR(36)")
    private UUID updatedBy;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Student() {
    }

    // Business methods
    public void addCourse(Course course) {
        if (course == null) {
            throw new IllegalArgumentException("Course cannot be null");
        }
        if (!this.courses.contains(course)) {
            this.courses.add(course);
            course.addStudent(this);
        }
    }

    public void removeCourse(Course course) {
        if (this.courses != null && this.courses.contains(course)) {
            this.courses.remove(course);
            course.removeStudent(this);
        }
    }

    // Getters and setters
    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNameOnCertificate() {
        return nameOnCertificate;
    }

    public void setNameOnCertificate(String nameOnCertificate) {
        this.nameOnCertificate = nameOnCertificate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Long getCurrentExamId() {
        return currentExamId;
    }

    public void setCurrentExamId(Long currentExamId) {
        this.currentExamId = currentExamId;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public String getProfileImageName() {
        return profileImageName;
    }

    public void setProfileImageName(String profileImageName) {
        this.profileImageName = profileImageName;
    }

    public String getProfileImageLink() {
        return profileImageLink;
    }

    public void setProfileImageLink(String profileImageLink) {
        this.profileImageLink = profileImageLink;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public String getExamPassword() {
        return examPassword;
    }

    public void setExamPassword(String examPassword) {
        this.examPassword = examPassword;
    }

    public Long getCurrentCourseId() {
        return currentCourseId;
    }

    public void setCurrentCourseId(Long currentCourseId) {
        this.currentCourseId = currentCourseId;
    }

    public String getCurrentCourseName() {
        return currentCourseName;
    }

    public void setCurrentCourseName(String currentCourseName) {
        this.currentCourseName = currentCourseName;
    }

    public ExamStatus getExamStatus() {
        return examStatus;
    }

    public void setExamStatus(ExamStatus examStatus) {
        this.examStatus = examStatus;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Franchise getFranchise() {
        return franchise;
    }

    public void setFranchise(Franchise franchise) {
        this.franchise = franchise;
    }

    public ExamType getExamType() {
        return examType;
    }

    public void setExamType(ExamType examType) {
        this.examType = examType;
    }

    public List<StudentExam> getStudentExams() {
        return studentExams;
    }

    public void setStudentExams(List<StudentExam> studentExams) {
        this.studentExams = studentExams;
    }

    // Audit getters/setters
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public UUID getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UUID createdBy) {
        this.createdBy = createdBy;
    }

    public UUID getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(UUID updatedBy) {
        this.updatedBy = updatedBy;
    }
}
