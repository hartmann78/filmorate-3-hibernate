package com.practice.filmorate3.user;

import com.practice.filmorate3.film.Film;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    private String login;
    private String name;
    private String email;
    private LocalDate birthday;

    @ManyToMany
    @JoinTable(name = "user_friendship",
            joinColumns = @JoinColumn(name = "first_user_id", referencedColumnName = "user_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "second_user_id", referencedColumnName = "user_id", nullable = false))
    private List<User> friends = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "user_liked_films",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id", nullable = false))
    private List<Film> likedFilms = new ArrayList<>();
}
