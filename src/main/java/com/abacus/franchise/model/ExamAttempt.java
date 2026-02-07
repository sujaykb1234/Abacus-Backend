package com.abacus.franchise.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "exam_attempt")
public class ExamAttempt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attempt_id")
    private Long attemptId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "exam_time")
    private String examTime;

    @Column(name = "exam_name")
    private String examName;

    @Column(name = "image_que_percentage")
    private int imageQuePercentage;

    /**
     * Stores question IDs related to this exam attempt
     */
    @ElementCollection
    @CollectionTable(
            name = "exam_attempt_questions",
            joinColumns = @JoinColumn(name = "attempt_id")
    )
    @Column(name = "question_id")
    private List<Long> questionIds;

    /**
     * Stores correct answers mapped by question ID
     */
    @ElementCollection
    @CollectionTable(
            name = "exam_attempt_answers",
            joinColumns = @JoinColumn(name = "attempt_id")
    )
    @MapKeyColumn(name = "question_id")
    @Column(name = "answer")
    private Map<Long, String> answers = new HashMap<>();

    /**
     * Stores student selected answers mapped by question ID
     */
    @ElementCollection
    @CollectionTable(
            name = "student_selected_answers",
            joinColumns = @JoinColumn(name = "attempt_id")
    )
    @MapKeyColumn(name = "question_id")
    @Column(name = "answer")
    private Map<Long, String> studentAnswers = new HashMap<>();

    @Column(name = "question_count")
    private int questionCount;

    @Column(name = "status")
    private boolean status = true;

    public ExamAttempt(
            Long attemptId,
            Course course,
            String courseName,
            String examTime,
            String examName,
            int imageQuePercentage,
            List<Long> questionIds,
            Map<Long, String> answers,
            Map<Long, String> studentAnswers,
            int questionCount
    ) {
        this.attemptId = attemptId;
        this.course = course;
        this.courseName = courseName;
        this.examTime = examTime;
        this.examName = examName;
        this.imageQuePercentage = imageQuePercentage;
        this.questionIds = questionIds;
        this.answers = answers;
        this.studentAnswers = studentAnswers;
        this.questionCount = questionCount;
    }
}
