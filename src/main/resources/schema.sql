CREATE TABLE IF NOT EXISTS events
(
    id BIGSERIAL UNIQUE PRIMARY KEY,
    description VARCHAR (255),
    book_title VARCHAR (255),
    book_author VARCHAR (255),
    book_publication_year VARCHAR (255),
    start_date VARCHAR (255),
    end_date VARCHAR (255),
    creator_id BIGSERIAL,
    category_name BIGSERIAL,
    CONSTRAINT fk_client FOREIGN KEY(creator_id) REFERENCES client(id)
);
