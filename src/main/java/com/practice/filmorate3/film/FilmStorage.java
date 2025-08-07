package com.practice.filmorate3.film;

import java.util.Collection;

public interface FilmStorage {
    Collection<Film> findAll();

    Film findFilmById(Long filmId);

    Collection<Film> topFilms(int count);

    Film createFilm(Film film);

    Film updateFilm(Film film);

    void likeFilm(Long filmId, Long userId);

    void dislikeFilm(Long filmId, Long userId);

    void deleteFilm(Film film);
}
