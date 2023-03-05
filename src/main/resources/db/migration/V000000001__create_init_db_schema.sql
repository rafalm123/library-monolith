DROP TABLE IF EXISTS book;
CREATE TABLE book(
                              id BIGSERIAL NOT NULL PRIMARY KEY,
                              book_uuid UUID NOT NULL UNIQUE,
                              author TEXT NOT NULL,
                              title TEXT NOT NULL,
                              publish_year BIGINT NOT NULL
);

DROP TABLE IF EXISTS book_release;
CREATE TABLE book_release(
                              id BIGSERIAL NOT NULL PRIMARY KEY,
                              isbn BIGINT NOT NULL,
                              book_release_uuid UUID NOT NULL UNIQUE,
                              pages BIGINT NOT NULL,
                              release_year BIGINT NOT NULL,
                              language TEXT NOT NULL
);

DROP TABLE IF EXISTS release_copy;
CREATE TABLE release_copy(
                           id BIGSERIAL NOT NULL PRIMARY KEY,
                           book_id BIGINT REFERENCES book(id),
                           book_copy_uuid UUID NOT NULL UNIQUE,
                           cover_type TEXT NOT NULL
);

DROP TABLE IF EXISTS release_copy_version;
CREATE TABLE release_copy_version(
                                id BIGSERIAL NOT NULL PRIMARY KEY,
                                book_copy_id BIGINT REFERENCES book_copy(id),
                                start_validity TIMESTAMP NOT NULL,
                                end_validity TIMESTAMP NOT NULL,
                                status VARCHAR(15) NOT NULL,
                                notes VARCHAR(200)
);

DROP TABLE IF EXISTS user_book_registry;
CREATE TABLE user_book_registry(
                                 id BIGSERIAL NOT NULL PRIMARY KEY,
                                 user_id BIGINT REFERENCES library_user(id),
                                 book_copy_id BIGINT REFERENCES book_copy(id),
                                 registry_uuid UUID NOT NULL unique,
                                 created_at TIMESTAMP NOT NULL,
                                 finish_at TIMESTAMP NOT NULL
);

DROP TABLE IF EXISTS library_user;
CREATE TABLE library_user(
                           id BIGSERIAL NOT NULL PRIMARY KEY,
                           user_uuid UUID NOT NULL UNIQUE,
                           creation_time TIMESTAMP NOT NULL,
                           user_type TEXT NOT NULL
);

DROP TABLE IF EXISTS library_user_version;
CREATE TABLE library_user_version (
                                   id BIGSERIAL NOT NULL PRIMARY KEY,
                                   user_id BIGINT REFERENCES library_user(id),
                                   nickname TEXT NOT NULL UNIQUE,
                                   email TEXT NOT NULL UNIQUE,
                                   address TEXT NOT NULL,
                                   postal_code TEXT NOT NULL,
                                   debt MONEY DEFAULT 0,
                                   start_validity TIMESTAMP NOT NULL,
                                   end_validity TIMESTAMP NOT NULL
);