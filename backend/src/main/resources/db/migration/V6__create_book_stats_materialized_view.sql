create materialized view category_stats as
select b.category                                       as category,
       count(b.id)                                      as total_books,
       coalesce(sum(b.available_copies), 0)             as total_available_copies,
       count(case when b.book_state = 'BAD' then 1 end) as bad_books
from books b
group by b.category;

create unique index category_stats_category_idx on category_stats (category);