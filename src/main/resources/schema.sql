CREATE TABLE IF NOT EXISTS events
(
    id BIGSERIAL UNIQUE PRIMARY KEY,
    event_title VARCHAR (255),
    description VARCHAR (1000),
    event_image VARCHAR (255),
    book_title VARCHAR (255),
    book_author VARCHAR (255),
    book_publication_year VARCHAR (255),
    start_date VARCHAR (255),
    end_date VARCHAR (255),
    created_date VARCHAR (255),
    user_id BIGSERIAL,
    category_name BIGSERIAL,
    active BOOLEAN,
    CONSTRAINT fk_client FOREIGN KEY(user_id) REFERENCES client(id)
);
