create table users
(
    id       integer generated always as identity
        constraint users_pkey
            primary key,
    email    varchar(50) not null
        constraint users_email_key
            unique,
    password varchar(50) not null,
    username varchar(250) default NULL::character varying,
    job      varchar(45)  default NULL::character varying,
    imgurl   text
);


INSERT INTO users(email, password, username, job, imgurl) VALUES

('timur@tinder.com','123','Timur','Python','https://robohash.org/24.218.243.24.png'),
('jalil@tinder.com','123','Jalil','Java','https://robohash.org/24.218.243.23.png'),
('alik@tinder.com','123','Alik','C#','https://robohash.org/24.218.243.25.png'),
('jim@tinder.com','123','Jim','Js','https://robohash.org/24.218.243.26.png'),
('jeremy@tinder.com','123','Jeremy','C++','https://robohash.org/24.218.243.27.png'),
('john@tinder.com','123','John','Kotlin','https://robohash.org/24.218.243.28.png');

alter table users
    owner to bchwpemrhwwufm;