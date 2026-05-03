create table unavailable_books_log
(
    id        bigserial primary key,
    book_id   bigint       not null,
    book_name varchar(255) not null,
    log_time  timestamptz  not null default now()
);