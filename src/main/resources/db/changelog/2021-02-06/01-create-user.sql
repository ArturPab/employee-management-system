--liquibase formatted sql
--changeset apabjan:1

CREATE TABLE USER (
    user_id bigint not null auto_increment,
    email varchar(100) not null UNIQUE,
    password varchar(255) not null,
    role varchar(30) not null,
    created datetime(6),
    enabled bit not null,
    primary key(user_id)
) engine=innoDB;

--changeset apabjan:2

insert into USER (user_id, email, password, role, created, enabled) values (null, 'admin@admin.com', '$2y$10$dodKvvTJtHA2.bASJ9f6EunfviXj8W.r8y6Mh9RMxQc04aPkczH/e', 'admin', '2021-01-18 19:27:05.068335', 1);
insert into USER (user_id, email, password, role, created, enabled) values (null, 'admin2@admin.com', 'test2', 'admin', '2021-01-31 19:27:05.068335', 1);