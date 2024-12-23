CREATE TABLE IF NOT EXISTS categories(
    id bigserial primary key not null,
    name varchar not null,
    active boolean default true not null,
    created_at timestamp with time zone default now(),
    updated_at timestamp with time zone default now()
);