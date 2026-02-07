package com.abacus.franchise.model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.sql.Timestamp;

import com.abacus.franchise.utility.ExamStatus;
import com.abacus.franchise.utility.ExamType;
import com.abacus.franchise.utility.ReattemptStatus;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "student_exam")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class StudentExam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attempt_id", nullable = false)
    private ExamAttempt examAttempt;

    @Enumerated(EnumType.STRING)
    @Column(name = "exam_status", nullable = false)
    private ExamStatus examStatus = ExamStatus.ASSIGNED;

    @Enumerated(EnumType.STRING)
    @Column(name = "exam_type")
    private ExamType examType;

    @Column(name = "mark_as_download")
    private Boolean markAsDownload = false;

    @Column(name = "franchise_id")
    private Long franchiseId;

    @Column(name = "exam_start_time")
    private String examStartTime;

    @Column(name = "exam_submission_time")
    private String examSubmissionTime;

    @Column(name = "marks")
    private Integer marks;

    @Column(name = "feedback", columnDefinition = "TEXT")
    private String feedback;

    @Column(name = "assigned_at", nullable = false)
    private Timestamp assignedAt;

    @Column(name = "completed_at")
    private Timestamp completedAt;

    @Column(name = "exam_time")
    private String examTime;

    @Column(name = "exam_name")
    private String examName;

    @Column(name = "question_count")
    private int questionCount;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "course_id")
    private Long courseId;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "exam_attempt_questions", joinColumns = @JoinColumn(name = "student_exam_id"))
    @Column(name = "question_id")
    private List<Long> questionIds;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "exam_attempt_answers", joinColumns = @JoinColumn(name = "student_exam_id"))
    @MapKeyColumn(name = "question_id")
    @Column(name = "answer")
    private Map<Long, String> answers = new HashMap<>();

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "student_selected_answers", joinColumns = @JoinColumn(name = "student_exam_id"))
    @MapKeyColumn(name = "question_id")
    @Column(name = "answer")
    private Map<Long, String> studentAnswers = new HashMap<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "reattempt_status")
    private ReattemptStatus reattemptStatus = ReattemptStatus.PENDING;

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

    public StudentExam() {
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public ExamAttempt getExamAttempt() {
        return examAttempt;
    }

    public void setExamAttempt(ExamAttempt examAttempt) {
        this.examAttempt = examAttempt;
    }

    public ExamStatus getExamStatus() {
        return examStatus;
    }

    public void setExamStatus(ExamStatus examStatus) {
        this.examStatus = examStatus;
    }

    public ExamType getExamType() {
        return examType;
    }

    public void setExamType(ExamType examType) {
        this.examType = examType;
    }

    public Boolean getMarkAsDownload() {
        return markAsDownload;
    }

    public void setMarkAsDownload(Boolean markAsDownload) {
        this.markAsDownload = markAsDownload;
    }

    public Long getFranchiseId() {
        return franchiseId;
    }

    public void setFranchiseId(Long franchiseId) {
        this.franchiseId = franchiseId;
    }

    public String getExamStartTime() {
        return examStartTime;
    }

    public void setExamStartTime(String examStartTime) {
        this.examStartTime = examStartTime;
    }

    public String getExamSubmissionTime() {
        return examSubmissionTime;
    }

    public void setExamSubmissionTime(String examSubmissionTime) {
        this.examSubmissionTime = examSubmissionTime;
    }

    public Integer getMarks() {
        return marks;
    }

    public void setMarks(Integer marks) {
        this.marks = marks;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Timestamp getAssignedAt() {
        return assignedAt;
    }

    public void setAssignedAt(Timestamp assignedAt) {
        this.assignedAt = assignedAt;
    }

    public Timestamp getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(Timestamp completedAt) {
        this.completedAt = completedAt;
    }

    public String getExamTime() {
        return examTime;
    }

    public void setExamTime(String examTime) {
        this.examTime = examTime;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public int getQuestionCount() {
        return questionCount;
    }

    public void setQuestionCount(int questionCount) {
        this.questionCount = questionCount;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public List<Long> getQuestionIds() {
        return questionIds;
    }

    public void setQuestionIds(List<Long> questionIds) {
        this.questionIds = questionIds;
    }

    public Map<Long, String> getAnswers() {
        return answers;
    }

    public void setAnswers(Map<Long, String> answers) {
        this.answers = answers;
    }

    public Map<Long, String> getStudentAnswers() {
        return studentAnswers;
    }

    public void setStudentAnswers(Map<Long, String> studentAnswers) {
        this.studentAnswers = studentAnswers;
    }

    public ReattemptStatus getReattemptStatus() {
        return reattemptStatus;
    }

    public void setReattemptStatus(ReattemptStatus reattemptStatus) {
        this.reattemptStatus = reattemptStatus;
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

    @Entity
    @Table(name = "student_practice_tests")
    public static class StudentPracticeTest {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long id;

        @Column(name = "exam_name")
        private String examName;

        @Column(name = "start_time")
        private String startTime;

        @Column(name = "course_id")
        private Long courseId;

        @Column(name = "total_question")
        private int totalQuestion;

        @Column(name = "submission_time")
        private String submissionTime;

        @Column(name = "score")
        private Double score;

        @Column(name = "student_id", nullable = false)
        private Long studentId;

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

        public StudentPracticeTest() {
        }

        // Getters and setters
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getExamName() {
            return examName;
        }

        public void setExamName(String examName) {
            this.examName = examName;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public Long getCourseId() {
            return courseId;
        }

        public void setCourseId(Long courseId) {
            this.courseId = courseId;
        }

        public int getTotalQuestion() {
            return totalQuestion;
        }

        public void setTotalQuestion(int totalQuestion) {
            this.totalQuestion = totalQuestion;
        }

        public String getSubmissionTime() {
            return submissionTime;
        }

        public void setSubmissionTime(String submissionTime) {
            this.submissionTime = submissionTime;
        }

        public Double getScore() {
            return score;
        }

        public void setScore(Double score) {
            this.score = score;
        }

        public Long getStudentId() {
            return studentId;
        }

        public void setStudentId(Long studentId) {
            this.studentId = studentId;
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
}
