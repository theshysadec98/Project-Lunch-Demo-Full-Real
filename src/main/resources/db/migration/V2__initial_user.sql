create table "user"
(
    id            bigserial
        constraint user_pk primary key,
    name          varchar(256)                           not null unique,
    email          varchar(256)                           not null unique,
    phone          varchar(256)                           not null unique,
    address          varchar(256)                           not null,
    dateofbirth      timestamp                              not null,
    created_date  timestamp    default current_timestamp not null,
    modified_date timestamp
);