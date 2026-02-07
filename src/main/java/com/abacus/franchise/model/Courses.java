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
@Table(name = "courses")
public class Courses {

		@Id
		@UuidGenerator
		@JdbcTypeCode(SqlTypes.VARCHAR)
		@Column(name = "course_id", length = 36, updatable = false, nullable = false)
		private UUID courseId;
		
		@Column(name = "course_name", nullable = false, length = 150)
		private String courseName;
	
		
		@Column(name = "duration_days", nullable = false)
		private int durationDays;
		
		@Column(name = "course_type", nullable = false, length = 50)
		private String courseType;
		
		@Column(name = "no_of_books", nullable = false)
		private int noOfBooks;
		
		@Column(name = "is_active", nullable = false)
		private Boolean isActive = true;
		
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

	    @PrePersist
	    protected void onCreate() {
	        this.createdAt = LocalDateTime.now();
	        this.updatedAt = LocalDateTime.now();
	    }

	    @PreUpdate
	    protected void onUpdate() {
	        this.updatedAt = LocalDateTime.now();
	    }

	    public UUID getCourseId() {
	        return courseId;
	    }

	    public void setCourseId(UUID courseId) {
	        this.courseId = courseId;
	    }

	    public String getCourseName() {
	        return courseName;
	    }

	    public void setCourseName(String courseName) {
	        this.courseName = courseName;
	    }

	    public int getDurationDays() {
	        return durationDays;
	    }

	    public void setDurationDays(int durationDays) {
	        this.durationDays = durationDays;
	    }

	    public String getCourseType() {
	        return courseType;
	    }

	    public void setCourseType(String courseType) {
	        this.courseType = courseType;
	    }

	    public int getNoOfBooks() {
			return noOfBooks;
		}

		public void setNoOfBooks(int noOfBooks) {
			this.noOfBooks = noOfBooks;
		}

		public Boolean getIsActive() {
	        return isActive;
	    }

	    public void setIsActive(Boolean active) {
	        isActive = active;
	    }

	    public LocalDateTime getCreatedAt() {
	        return createdAt;
	    }

	    public LocalDateTime getUpdatedAt() {
	        return updatedAt;
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
