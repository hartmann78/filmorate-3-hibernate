package com.practice.filmorate3.user;

import com.practice.filmorate3.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository("userDbStorage")
@RequiredArgsConstructor
public class UserDbStorage implements UserStorage {
    private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");
    protected EntityManager em = factory.createEntityManager();

    @Override
    public Collection<User> findAll() {
        return em.createQuery("from User", User.class).getResultList();
    }

    @Override
    public User findUserById(Long userId) {
        return em.find(User.class, userId);
    }

    @Override
    public Collection<User> getFriendsList(Long userId) {
        return em.createQuery("from User u, IN (u.friends) AS f where u.user_id = :userId", User.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public Collection<User> commonFriends(Long userId, Long otherId) {
        return List.of();
    }

    @Override
    public User createUser(User user) {
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.flush();
            em.getTransaction().commit();
            return user;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return user;
        }
    }

    @Override
    public User updateUser(User user) {
        if (findUserById(user.getId()) == null) {
            throw new NotFoundException("Не существует в базе пользователя с id " + user.getId());
        }

        try {
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
            return user;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return user;
        }
    }

    @Override
    public void deleteUser(User user) {
        try {
            em.getTransaction().begin();
            em.remove(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void addFriend(Long userId, Long friendId) {
        User user = findUserById(userId);
        if (user == null) {
            throw new NotFoundException("Не существует в базе пользователя с id " + userId);
        }

        User friend = findUserById(friendId);
        if (friend == null) {
            throw new NotFoundException("Не существует в базе пользователя с id " + friendId);
        }

        try {
            em.getTransaction().begin();
            user.getFriends().add(friend);
            em.merge(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void deleteFriend(Long userId, Long friendId) {
        User user = findUserById(userId);
        if (user == null) {
            throw new NotFoundException("Не существует в базе пользователя с id " + userId);
        }

        User friend = findUserById(friendId);
        if (friend == null) {
            throw new NotFoundException("Не существует в базе пользователя с id " + friendId);
        }

        try {
            em.getTransaction().begin();
            user.getFriends().remove(friend);
            em.merge(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}
