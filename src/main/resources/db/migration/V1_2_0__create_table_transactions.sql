CREATE TABLE IF NOT EXISTS transactions(
    id bigserial primary key not null,
    description varchar,
    type varchar not null,
    category_id integer not null,
    FOREIGN KEY (category_id) REFERENCES categories(id),
    user_id integer not null,
    FOREIGN KEY (user_id) REFERENCES users(id),
    price numeric(5,2) not null,
    active boolean default true,
    created_at timestamp with time zone default now(),
    updated_at timestamp with time zone default now()
);