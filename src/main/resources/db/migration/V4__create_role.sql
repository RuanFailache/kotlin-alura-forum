CREATE TABLE role
(
    id   bigint not null auto_increment,
    name varchar(50),
    primary key (id)
);

INSERT INTO role
values (1, 'READ_AND_WRITE');

INSERT INTO role
values (2, 'READ_ONLY');
