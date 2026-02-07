package com.abacus.franchise.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "franchise_courses")
public class FranchiseCourse {

    @Id
    @UuidGenerator
	@JdbcTypeCode(SqlTypes.VARCHAR)
	@Column(name = "franchise_course_id", length = 36, updatable = false, nullable = false)
    private UUID franchiseCourseId;

	@JdbcTypeCode(SqlTypes.VARCHAR)
	@Column(name = "franchise_id", length = 36, updatable = false, nullable = false)
    private UUID franchiseId;

	@JdbcTypeCode(SqlTypes.VARCHAR)
	@Column(name = "course_id", length = 36, updatable = false, nullable = false)
    private UUID courseId;

    @Column(name = "assign_date")
    private LocalDateTime assignDate;
    
    @Column(name = "created_at", updatable = false)
	private LocalDateTime createdAt;
	
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
	
	@JdbcTypeCode(SqlTypes.VARCHAR)
	@Column(name = "created_by", length = 36)
	private UUID createdBy;
	
	@JdbcTypeCode(SqlTypes.VARCHAR)
	@Column(name = "updated_by", length = 36)
	private UUID updatedBy;
	
	@Column(name = "courses_status", nullable = false)
	private Boolean coursesStatus = true;
	
	@PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
    
    
	public Boolean getCoursesStatus() {
		return coursesStatus;
	}

	public void setCoursesStatus(Boolean coursesStatus) {
		this.coursesStatus = coursesStatus;
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

	public UUID getFranchiseCourseId() {
		return franchiseCourseId;
	}

	public void setFranchiseCourseId(UUID franchiseCourseId) {
		this.franchiseCourseId = franchiseCourseId;
	}

	public UUID getFranchiseId() {
		return franchiseId;
	}

	public void setFranchiseId(UUID franchiseId) {
		this.franchiseId = franchiseId;
	}

	public UUID getCourseId() {
		return courseId;
	}

	public void setCourseId(UUID courseId) {
		this.courseId = courseId;
	}

	public LocalDateTime getAssignDate() {
		return assignDate;
	}

	public void setAssignDate(LocalDateTime assignDate) {
		this.assignDate = assignDate;
	}
    
    
}
