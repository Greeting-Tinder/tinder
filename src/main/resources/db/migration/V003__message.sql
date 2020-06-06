create table message
(
    id        integer generated always as identity
        constraint message_pkey
            primary key,
    user_to   integer,
    user_from integer,
    message   text,
    localid   integer,
    datetime  text
);

