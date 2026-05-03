create table authors
(
    id         bigserial primary key,
    name       varchar(255) not null,
    surname    varchar(255) not null,
    country_id bigint       not null references countries (id) on delete cascade,
    created_at timestamptz  not null default now(),
    updated_at timestamptz  not null default now()
);