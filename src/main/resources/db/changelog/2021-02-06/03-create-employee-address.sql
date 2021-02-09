--liquibase formatted sql
--changeset apabjan:5

create table employee_address (
    address_id bigint not null auto_increment,
    street varchar(60) not null,
    house_number varchar(8) not null,
    city varchar(40) not null,
    zip_code varchar(7) not null,
    employee_id bigint not null,
    primary key(address_id)
) engine=InnoDB;

alter table employee_address add constraint FK2 foreign key (employee_id) references employee(employee_id);

--changeset apabjan:6
insert into employee_address (address_id, street, house_number, city, zip_code, employee_id) VALUES (null, 'Street', '60/60', 'Tychy', '43-100', 1);
insert into employee_address (address_id, street, house_number, city, zip_code, employee_id) VALUES (null, 'Street', '60/60', 'Tychy', '43-100', 2);
