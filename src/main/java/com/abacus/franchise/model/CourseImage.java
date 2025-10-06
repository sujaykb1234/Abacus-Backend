package com.abacus.franchise.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CourseImage{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long image_id;
	private Long course_id;
	private String image_link;
	private String image_name;
	
	public Long getImage_id() {
		return image_id;
	}
	public void setImage_id(Long image_id) {
		this.image_id = image_id;
	}
	public Long getCourse_id() {
		return course_id;
	}
	public void setCourse_id(Long course_id) {
		this.course_id = course_id;
	}
	public String getImage_link() {
		return image_link;
	}
	public void setImage_link(String image_link) {
		this.image_link = image_link;
	}
	public String getImage_name() {
		return image_name;
	}
	public void setImage_name(String image_name) {
		this.image_name = image_name;
	}

	
	

	
}
