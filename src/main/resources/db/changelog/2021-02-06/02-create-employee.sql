--liquibase formatted sql
--changeset apabjan:3

create table EMPLOYEE (
    employee_id bigint not null auto_increment,
    name varchar(40) not null,
    last_name varchar(40) not null,
    birthdate date not null,
    user_id bigint not null,
    primary key (employee_id)
) engine=innoDB;

alter table EMPLOYEE add constraint FK1 foreign key (user_id) references USER(user_id);

--changeset apabjan:4

insert into EMPLOYEE (employee_id, name, last_name, birthdate, user_id) values (null, 'Artur', 'Pabjan', '2000-09-05', 1);
insert into EMPLOYEE (employee_id, name, last_name, birthdate, user_id) values (null, 'Artur', 'Pabjans', '2000-09-05', 2);