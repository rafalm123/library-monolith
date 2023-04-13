CREATE SEQUENCE IF NOT EXISTS book_id_seq;

DROP TABLE IF EXISTS book;
CREATE TABLE book(
                     id BIGINT NOT NULL PRIMARY KEY,
                     author TEXT NOT NULL,
                     title TEXT NOT NULL,
                     publish_year INTEGER NOT NULL,
                     UNIQUE (author, title)
);

DROP TABLE IF EXISTS book_release;
CREATE TABLE book_release(
                             id BIGINT NOT NULL PRIMARY KEY,
                             isbn BIGINT NOT NULL,
                             pages INTEGER NOT NULL,
                             release_year INTEGER NOT NULL,
                             language TEXT NOT NULL,
                             book_id BIGINT REFERENCES book(id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS release_copy;
CREATE TABLE release_copy(
                             id BIGINT NOT NULL PRIMARY KEY,
                             book_release_id BIGINT REFERENCES book_release(id) ON DELETE CASCADE,
                             cover_type TEXT NOT NULL
);

DROP TABLE IF EXISTS release_copy_version;
CREATE TABLE release_copy_version(
                                     id BIGINT NOT NULL PRIMARY KEY,
                                     release_copy_id BIGINT REFERENCES release_copy(id) ON DELETE CASCADE,
                                     start_validity TIMESTAMP NOT NULL,
                                     end_validity TIMESTAMP NOT NULL,
                                     status VARCHAR(15) NOT NULL,
                                     notes VARCHAR(200)
);

DROP TABLE IF EXISTS library_user;
CREATE TABLE library_user (
                              id BIGINT NOT NULL PRIMARY KEY,
                              username TEXT NOT NULL UNIQUE,
                              password TEXT NOT NULL,
                              library_code BIGINT NOT NULL UNIQUE,
                              create_date TIMESTAMP NOT NULL
);

DROP TABLE IF EXISTS library_user_version;
CREATE TABLE library_user_version (
                                      id BIGINT NOT NULL PRIMARY KEY,
                                      library_user_id BIGINT REFERENCES library_user(id),
                                      nickname TEXT NOT NULL UNIQUE,
                                      user_name TEXT NOT NULL,
                                      user_surname TEXT NOT NULL,
                                      email TEXT NOT NULL UNIQUE,
                                      debt DECIMAL DEFAULT 0,
                                      start_validity TIMESTAMP NOT NULL,
                                      end_validity TIMESTAMP NOT NULL
);

DROP TABLE IF EXISTS address;
CREATE TABLE address (
                         id BIGINT NOT NULL PRIMARY KEY,
                         library_user_version_id BIGINT REFERENCES library_user_version(id),
                         street TEXT NOT NULL,
                         city TEXT NOT NULL,
                         state TEXT NOT NULL,
                         postal_code TEXT NOT NULL,
                         country TEXT NOT NULL
);

DROP TABLE IF EXISTS role;
CREATE TABLE role (
                      id BIGINT NOT NULL PRIMARY KEY,
                      name VARCHAR(20) NOT NULL
);

INSERT INTO role (id, name) VALUES (1, 'REGULAR');
INSERT INTO role (id, name) VALUES (2, 'ADMIN');
