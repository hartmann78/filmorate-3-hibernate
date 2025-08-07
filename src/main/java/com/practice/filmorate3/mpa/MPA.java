package com.practice.filmorate3.mpa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "mpa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MPA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mpa_id;
    private String name;


    private Long film_id;
}
