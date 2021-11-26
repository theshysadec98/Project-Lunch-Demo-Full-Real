create table "food"
(
    id            bigserial
        constraint food_pk primary key,
    name          varchar(256)                           not null unique,
    taste          varchar(256)                           not null,
    description    varchar(256)                           not null,
    quantity          int                           not null,
    price          int                           not null,
    created_date  timestamp    default current_timestamp not null,
    modified_date timestamp
);