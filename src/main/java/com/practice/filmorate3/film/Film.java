package com.practice.filmorate3.film;

import com.practice.filmorate3.genre.Genre;
import com.practice.filmorate3.mpa.MPA;
import com.practice.filmorate3.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "films")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private Long id;
    private int likes;
    private String name;
    private String description;
    private LocalDate releaseDate;
    private Duration duration;

    @ManyToMany
    @JoinTable(name = "film_liked_users",
            joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false))
    private List<User> likedUsers = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "films_genres",
            joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "genre_id", referencedColumnName = "genre_id", nullable = false))
    private Set<Genre> genres = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "mpa_id")
    private MPA mpa;

    public long getDuration() {
        return duration.getSeconds();
    }
}
