CREATE TABLE author
(
  author_id   SERIAL,
  author_name VARCHAR(127) NOT NULL,
  UNIQUE (author_name),
  PRIMARY KEY (author_id)
);

CREATE TABLE publisher
(
  publisher_id   SERIAL,
  publisher_name VARCHAR(127) NOT NULL,
  UNIQUE (publisher_name),
  PRIMARY KEY (publisher_id)
);

CREATE TABLE category
(
  category_id   SERIAL,
  category_name VARCHAR(127) NOT NULL,
  UNIQUE (category_name),
  PRIMARY KEY (category_id)
);

CREATE TABLE library
(
  library_id   SERIAL,
  library_name VARCHAR(127) NOT NULL,
  adress       VARCHAR(127) NOT NULL,
  UNIQUE (library_name),
  PRIMARY KEY (library_id)
);

CREATE TABLE book
(
  book_id      SERIAL,
  title        VARCHAR(127) NOT NULL,
  description  VARCHAR(511) DEFAULT NULL,
  year         SMALLINT CONSTRAINT real_year CHECK (year > 0 AND year < 2100),
  publisher_id INT,
  PRIMARY KEY (book_id),
  FOREIGN KEY (publisher_id) REFERENCES publisher (publisher_id)
  ON DELETE RESTRICT
  ON UPDATE CASCADE
);

CREATE TABLE book_category
(
  book_id     INT NOT NULL,
  category_id INT NOT NULL,
  PRIMARY KEY (book_id, category_id),
  FOREIGN KEY (book_id) REFERENCES book (book_id)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
  FOREIGN KEY (category_id) REFERENCES category (category_id)
  ON DELETE RESTRICT
  ON UPDATE CASCADE
);

CREATE TABLE book_author
(
  book_id   INT NOT NULL,
  author_id INT NOT NULL,
  PRIMARY KEY (book_id, author_id),
  FOREIGN KEY (book_id) REFERENCES book (book_id)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
  FOREIGN KEY (author_id) REFERENCES author (author_id)
  ON DELETE RESTRICT
  ON UPDATE CASCADE
);

CREATE TABLE library_book
(
  library_id     INT NOT NULL,
  book_id        INT NOT NULL,
  nubmer_of_book INT NOT NULL CONSTRAINT positive_number CHECK (library_book.nubmer_of_book > 0),
  FOREIGN KEY (library_id) REFERENCES library (library_id)
  ON DELETE RESTRICT
  ON UPDATE CASCADE,
  FOREIGN KEY (book_id) REFERENCES book (book_id)
  ON DELETE RESTRICT
  ON UPDATE CASCADE
);