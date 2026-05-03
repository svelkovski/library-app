create table activity_log
(
    id            bigserial primary key,
    book_name     varchar(255) not null,
    occurred_at   timestamptz  not null default now(),
    activity_type varchar(50)  not null
);