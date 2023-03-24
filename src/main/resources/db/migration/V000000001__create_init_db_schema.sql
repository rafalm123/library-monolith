DROP TABLE IF EXISTS book;
CREATE TABLE book(
                              id BIGSERIAL NOT NULL PRIMARY KEY,
                              author TEXT NOT NULL,
                              title TEXT NOT NULL,
                              publish_year INTEGER NOT NULL
);

DROP TABLE IF EXISTS book_release;
CREATE TABLE book_release(
                              id BIGSERIAL NOT NULL PRIMARY KEY,
                              isbn BIGINT NOT NULL,
                              pages INTEGER NOT NULL,
                              release_year INTEGER NOT NULL,
                              language TEXT NOT NULL,
                              book_id BIGINT REFERENCES book(id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS release_copy;
CREATE TABLE release_copy(
                           id BIGSERIAL NOT NULL PRIMARY KEY,
                           book_release_id BIGINT REFERENCES book_release(id) ON DELETE CASCADE,
                           cover_type TEXT NOT NULL
);

DROP TABLE IF EXISTS release_copy_version;
CREATE TABLE release_copy_version(
                                id BIGSERIAL NOT NULL PRIMARY KEY,
                                release_copy_id BIGINT REFERENCES release_copy(id) ON DELETE CASCADE,
                                start_validity TIMESTAMP NOT NULL,
                                end_validity TIMESTAMP NOT NULL,
                                status VARCHAR(15) NOT NULL,
                                notes VARCHAR(200)
);
