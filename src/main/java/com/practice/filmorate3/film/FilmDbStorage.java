package com.practice.filmorate3.film;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.*;

@RequiredArgsConstructor
@Repository("filmDbStorage")
public class FilmDbStorage implements FilmStorage {
    private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");
    protected EntityManager em = factory.createEntityManager();

    @Override
    public Collection<Film> findAll() {
        return em.createQuery("from films", Film.class).getResultList();
    }

    @Override
    public Film findFilmById(Long filmId) {
        return em.find(Film.class, filmId);
    }

    @Override
    public Collection<Film> topFilms(int count) {
        return List.of();
    }

    @Override
    public Film createFilm(Film film) {
        try {
            em.getTransaction().begin();
            em.persist(film);
            em.flush();
            em.getTransaction().commit();
            return film;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return film;
        }
    }

    @Override
    public Film updateFilm(Film film) {
        try {
            em.getTransaction().begin();
            em.merge(film);
            em.getTransaction().commit();
            return film;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return film;
        }
    }

    @Override
    public void deleteFilm(Film film) {
        try {
            em.getTransaction().begin();
            em.remove(film);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void likeFilm(Long filmId, Long userId) {

    }

    @Override
    public void dislikeFilm(Long filmId, Long userId) {

    }
}
