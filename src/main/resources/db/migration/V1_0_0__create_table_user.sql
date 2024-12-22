CREATE TABLE IF NOT EXISTS users(
    id bigserial primary key not null,
    name varchar not null,
    mail varchar unique not null,
    password varchar not null,
    active boolean default true,
    created_at timestamp with time zone default now(),
    updated_at timestamp with time zone default now()
);