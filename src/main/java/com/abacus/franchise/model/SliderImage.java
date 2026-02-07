package com.abacus.franchise.model;

import jakarta.persistence.*;

@Entity
@Table(name = "slider_image")
public class SliderImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "slider_image_id")
    private Long sliderImageId;

    @Column(name = "slider_image_link", nullable = false)
    private String sliderImageLink;

    @Column(name = "slider_image_name", nullable = false)
    private String sliderImageName;

    // Getters and setters
    public Long getSliderImageId() {
        return sliderImageId;
    }

    public void setSliderImageId(Long sliderImageId) {
        this.sliderImageId = sliderImageId;
    }

    public String getSliderImageLink() {
        return sliderImageLink;
    }

    public void setSliderImageLink(String sliderImageLink) {
        this.sliderImageLink = sliderImageLink;
    }

    public String getSliderImageName() {
        return sliderImageName;
    }

    public void setSliderImageName(String sliderImageName) {
        this.sliderImageName = sliderImageName;
    }
}
