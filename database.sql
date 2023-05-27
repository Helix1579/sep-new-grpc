create sequence hibernate_sequence;

alter sequence hibernate_sequence owner to postgres;

create table if not exists projects
(
    id           integer not null
        primary key,
    project_name varchar(255),
    is_completed boolean,
    owner        varchar(255)
);

alter table projects
    owner to postgres;

create table if not exists tasks
(
    id           integer not null
        primary key,
    is_completed boolean not null,
    project_id   integer not null
        constraint project_id
            references projects
            on update cascade on delete cascade,
    title        varchar(255)
);

alter table tasks
    owner to postgres;

create table if not exists users
(
    id       integer not null
        primary key,
    password varchar(255),
    username varchar(255)
);

alter table users
    owner to postgres;

create table if not exists projects_users_of_project
(
    projects_id         integer not null
        constraint fk7xpesia8pk4cjyjo0yfkc3800
            references projects
            on update cascade on delete cascade,
    users_of_project_id integer not null
        constraint uk_n8e6oct3n56gnq8c93hk7mx7a
            unique
        constraint fkhdndmogk45m71whxcqmolmwa1
            references users
            on update cascade on delete cascade
);

alter table projects_users_of_project
    owner to postgres;

create table if not exists tasks_tasks_of_project
(
    tasks_id            integer not null
        constraint fkouq6jr8vf66tstxu9n99eo0px
            references tasks
            on update cascade on delete cascade,
    tasks_of_project_id integer not null
        constraint uk_3fnbqsh7pfk07sovx668cp49q
            unique
        constraint fk52po9knpqb8mq6cvg25w4lrc9
            references projects
            on update cascade on delete cascade
);

alter table tasks_tasks_of_project
    owner to postgres;

