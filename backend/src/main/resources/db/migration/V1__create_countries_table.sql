create table countries
(
    id         bigserial primary key,
    name       varchar(255) not null unique,
    continent  varchar(255) not null,
    created_at timestamptz  not null default now(),
    updated_at timestamptz  not null default now()
);