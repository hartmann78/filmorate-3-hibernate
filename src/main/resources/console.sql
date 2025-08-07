drop table if exists mpa;

drop table if exists user_liked_films;

drop table if exists film_liked_users;

drop table if exists user_friendship;

drop table if exists films_genres;

drop table if exists users;

drop table if exists films;

drop table if exists genre;

create table users
(
    user_id  long auto_increment primary key,
    login    varchar,
    name     varchar,
    email    varchar,
    birthday date
);

create table films
(
    film_id      long auto_increment primary key,
    likes        integer,
    name         varchar,
    description  varchar,
    release_date date,
    duration     long
);

create table genre
(
    genre_id long auto_increment primary key,
    name     varchar not null unique
);

create table mpa
(
    mpa_id  long auto_increment primary key,
    name    varchar                         not null unique,
    film_id long references films (film_id) not null
);

create table user_friendship
(
    first_user_id  long references users (user_id) check (first_user_id != second_user_id) not null,
    second_user_id long references users (user_id)                                         not null
);

create table user_liked_films
(
    user_id long references users (user_id) not null,
    film_id long references films (film_id) not null
);

create table film_liked_users
(
    film_id long references films (film_id) not null,
    user_id long references users (user_id) not null
);

create table films_genres
(
    film_id  long references films (film_id),
    genre_id long references genre (genre_id)
);