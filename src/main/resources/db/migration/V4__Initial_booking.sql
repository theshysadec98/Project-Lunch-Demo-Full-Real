create table "booking"
(
    id            bigserial
        constraint booking_pk primary key,
    name          varchar(256)                    not null unique,
    user_id          int                          not null,
    created_date  timestamp    default current_timestamp not null,
    modified_date timestamp,
    constraint user_pk foreign key(user_id)
        references "user"(id)
);