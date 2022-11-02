create table author
(
    id    bigint      not null auto_increment,
    name  varchar(50) not null,
    email varchar(50) not null,
    primary key (id)
);

create table course
(
    id       bigint      not null auto_increment,
    name     varchar(50) not null,
    category varchar(50) not null,
    primary key (id)
);


create table topic
(
    id         bigint       not null auto_increment,
    title      varchar(50)  not null,
    message    varchar(300) not null,
    created_at datetime     not null,
    author_id  bigint       not null,
    course_id  bigint       not null,
    status     varchar(50)  not null,
    primary key (id),
    foreign key (author_id) references author (id),
    foreign key (course_id) references course (id)
);

create table answer
(
    id          bigint       not null auto_increment,
    message     varchar(300) not null,
    created_at  datetime     not null,
    topic_id    bigint       not null,
    author_id   bigint       not null,
    is_solution boolean      not null,
    primary key (id),
    foreign key (topic_id) references topic (id),
    foreign key (author_id) references author (id)
);
