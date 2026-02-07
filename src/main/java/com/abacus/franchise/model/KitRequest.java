package com.abacus.franchise.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class KitRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long courseId;
    private Integer kitCount;

    public KitRequest(Long courseId, Integer kitCount) {
        this.courseId = courseId;
        this.kitCount = kitCount;
    }
}
