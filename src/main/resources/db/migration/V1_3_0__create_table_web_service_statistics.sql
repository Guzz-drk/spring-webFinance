CREATE TABLE IF NOT EXISTS web_services_statistics(
    id bigserial primary key not null,
    created_at timestamp with time zone default now(),
    ws_called varchar not null,
    user_id integer not null,
    FOREIGN KEY (user_id) REFERENCES users(id)
);