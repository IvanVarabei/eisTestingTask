create table policy
(
    id   serial      not null primary key,
    name varchar(64) not null,
    constraint unique_tag_name unique (name)
);

create table books
(
    id     bigint not null
        constraint books_pkey
            primary key,
    author varchar(255),
    title  varchar(255)
);


INSERT INTO books (
    id, title, author)
VALUES (1, 'Madol DuwaUUU', 'Martin Wickramasinghe'),
       (2, 'Gamperaliya', 'Martin Wickramasinghe');