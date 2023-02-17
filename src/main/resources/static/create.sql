set search_path to library;

CREATE TABLE library.book (
                              id BIGSERIAL NOT NULL PRIMARY KEY,
                              isbn BIGINT NOT NULL,
                              book_uuid UUID NOT NULL UNIQUE,
                              author TEXT NOT NULL,
                              title TEXT NOT NULL,
                              pages BIGINT NOT NULL,
                              release_year BIGINT NOT NULL
);

CREATE TABLE book_copy (
                           id BIGSERIAL NOT NULL PRIMARY KEY,
                           book_id BIGINT REFERENCES book(id),
                           book_copy_uuid UUID NOT NULL UNIQUE,
                           copy_language TEXT NOT NULL,
                           cover_type TEXT NOT NULL
);

CREATE TABLE book_copy_version (
                                   id BIGSERIAL NOT NULL PRIMARY KEY,
                                   book_copy_id BIGINT REFERENCES book_copy(id),
                                   start_validity TIMESTAMP NOT NULL,
                                   end_validity TIMESTAMP NOT NULL,
                                   status VARCHAR(15) NOT NULL,
                                   notes VARCHAR(200)
);

CREATE TABLE user_book_registry (
                                    id BIGSERIAL NOT NULL PRIMARY KEY,
                                    user_id BIGINT REFERENCES library_user(id),
                                    book_copy_id BIGINT REFERENCES book_copy(id),
                                    registry_uuid UUID NOT NULL unique,
                                    created_at TIMESTAMP NOT NULL,
                                    finish_at TIMESTAMP NOT NULL
);

CREATE TABLE library_user (
                              id BIGSERIAL NOT NULL PRIMARY KEY,
                              user_uuid UUID NOT NULL UNIQUE,
                              creation_time TIMESTAMP NOT NULL,
                              user_type TEXT NOT NULL
)	;

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