package com.abacus.franchise.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "course_image")
public class CourseImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long imageId;

    @Column(name = "course_id", nullable = false)
    private Long courseId;

    @Column(name = "image_link", nullable = false, length = 255)
    private String imageLink;

    @Column(name = "image_name", length = 150)
    private String imageName;
}
