package com.abacus.franchise.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class SliderImage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long slider_image_id;
	private String slider_image_link;
	private String slider_image_name;

	public Long getSlider_image_id() {
		return slider_image_id;
	}

	public void setSlider_image_id(Long slider_image_id) {
		this.slider_image_id = slider_image_id;
	}

	public String getSlider_image_link() {
		return slider_image_link;
	}

	public void setSlider_image_link(String slider_image_link) {
		this.slider_image_link = slider_image_link;
	}

	public String getSlider_image_name() {
		return slider_image_name;
	}

	public void setSlider_image_name(String slider_image_name) {
		this.slider_image_name = slider_image_name;
	}
	
	

	
}
