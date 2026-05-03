create or replace function update_updated_at_column()
    returns trigger as
$$
begin
    new.updated_at = now();
    return new;
end;
$$ language plpgsql;

create trigger update_countries_updated_at
    before update
    on countries
    for each row
execute function update_updated_at_column();

create trigger update_authors_updated_at
    before update
    on authors
    for each row
execute function update_updated_at_column();

create trigger update_books_updated_at
    before update
    on books
    for each row
execute function update_updated_at_column();