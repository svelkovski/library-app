create table books
(
    id               bigserial primary key,
    name             varchar(255) not null,
    category         varchar(255) not null check (category in
                                                  ('NOVEL', 'THRILLER', 'HISTORY', 'FANTASY', 'BIOGRAPHY', 'CLASSICS',
                                                   'DRAMA')),
    author_id        bigint       not null references authors (id) on delete cascade,
    book_state       varchar(255) not null check (book_state in ('GOOD', 'BAD')),
    available_copies int          not null,
    created_at       timestamptz  not null default now(),
    updated_at       timestamptz  not null default now()
);