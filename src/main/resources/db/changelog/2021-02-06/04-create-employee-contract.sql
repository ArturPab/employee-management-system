--liquibase formatted sql
--changeset apabjan:7

create table employee_contract (
    employee_contract_id bigint not null auto_increment,
    job_position varchar(30) not null,
    contract_type varchar(30) not null,
    salary_in_pln int not null,
    employee_id bigint not null,
    primary key(employee_contract_id)
) ENGINE=InnoDB;

alter table employee_contract add constraint FK3 foreign key (employee_id) references employee(employee_id);

--liquibase formatted sql
--changeset apabjan:8

insert into employee_contract (employee_contract_id, job_position, contract_type, salary_in_pln, employee_id) VALUES (1, 'Junior Java Developer', 'B2B', 6000, 1);
