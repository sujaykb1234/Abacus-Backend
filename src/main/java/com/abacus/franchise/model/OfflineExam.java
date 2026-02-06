package com.abacus.franchise.model;

import java.time.LocalDateTime;
import java.util.List;

import com.abacus.franchise.utility.ExamType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;

@Entity
public class OfflineExam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long offlineExamId;

    @Column(nullable = false)
    private Long examId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ExamType examType;

    @Column(nullable = false)
    private Long studentId;

    @Column(nullable = false)
    private Long franchiseId;

    @Column(nullable = false)
    private Long courseId;

    private Double marks;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "offline_exam_images",
            joinColumns = @JoinColumn(name = "offline_exam_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id")
    )
    private List<StoredImages> pdfImages;

    @Column(nullable = false)
    private Boolean markAsDownload = false;

    @Column(nullable = false)
    private LocalDateTime creationTime;

    @Column(nullable = false)
    private Boolean status = true;

    // ======================
    // GETTERS & SETTERS
    // ======================

    public Long getOfflineExamId() {
        return offlineExamId;
    }

    public void setOfflineExamId(Long offlineExamId) {
        this.offlineExamId = offlineExamId;
    }

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    public ExamType getExamType() {
        return examType;
    }

    public void setExamType(ExamType examType) {
        this.examType = examType;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getFranchiseId() {
        return franchiseId;
    }

    public void setFranchiseId(Long franchiseId) {
        this.franchiseId = franchiseId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Double getMarks() {
        return marks;
    }

    public void setMarks(Double marks) {
        this.marks = marks;
    }

    public List<StoredImages> getPdfImages() {
        return pdfImages;
    }

    public void setPdfImages(List<StoredImages> pdfImages) {
        this.pdfImages = pdfImages;
    }

    public Boolean getMarkAsDownload() {
        return markAsDownload;
    }

    public void setMarkAsDownload(Boolean markAsDownload) {
        this.markAsDownload = markAsDownload;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
