CREATE TABLE author_role
(
    id        bigint not null auto_increment,
    author_id bigint not null,
    role_id   bigint not null,
    primary key (id),
    foreign key (author_id) references author (id),
    foreign key (role_id) references role (id)
);

INSERT INTO author_role
values (1, 1, 1);

INSERT INTO author_role
values (2, 2, 1);

INSERT INTO author_role
values (3, 3, 2);