create table likes
(
    id         integer generated always as identity
        constraint likes_pkey
            primary key,
    user_likes integer,
    user_liked integer
);

