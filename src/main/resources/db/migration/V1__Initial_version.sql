create table pokemon
(
    id            bigserial
        constraint pokemon_pk primary key,
    name          varchar(256)                           not null unique,
    type          varchar(64),
    created_date  timestamp    default current_timestamp not null,
    created_by    varchar(128) default 'system'          not null,
    modified_date timestamp,
    modified_by   varchar(128)
);


