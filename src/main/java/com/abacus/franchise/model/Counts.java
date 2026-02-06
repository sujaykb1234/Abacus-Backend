package com.abacus.franchise.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "counts")
public class Counts {

    @Id
    @Column(name = "count_id")
    private Long countId;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "abbreviation", length = 20)
    private String abbreviation;

    @Column(name = "count_value")
    private Long count;
}
