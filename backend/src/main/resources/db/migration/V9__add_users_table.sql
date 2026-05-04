create table users
(
    id            bigserial primary key,
    first_name    varchar(255) not null,
    last_name     varchar(255) not null,
    email         varchar(255) not null unique,
    password_hash varchar(255) not null,
    role          varchar(50)  not null default 'USER' check (role in ('USER', 'ADMIN')),
    created_at    timestamptz  not null default now(),
    updated_at    timestamptz  not null default now()
);

create trigger update_users_updated_at
    before update
    on users
    for each row
execute function update_updated_at_column();