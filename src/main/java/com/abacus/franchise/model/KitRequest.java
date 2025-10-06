package com.abacus.franchise.model;

public class KitRequest {
	private Long courseId;
	private Integer kitCount;

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public Integer getKitCount() {
		return kitCount;
	}

	public void setKitCount(Integer kitCount) {
		this.kitCount = kitCount;
	}
}
