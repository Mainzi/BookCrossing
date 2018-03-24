CREATE VIEW get_categories AS
  SELECT category.category_name
  FROM category
  ORDER BY category.category_name;

CREATE VIEW get_libraries AS
  SELECT library.library_name
  FROM library
  ORDER BY library.library_name;

CREATE VIEW get_publishers AS
  SELECT publisher.publisher_name
  FROM publisher
  ORDER BY publisher.publisher_name;

CREATE VIEW get_publishers_with_books AS
  SELECT
    p.publisher_id,
    ARRAY(SELECT b.title
          FROM book b
          WHERE (p.publisher_id = b.publisher_id)) AS "array"
  FROM publisher p;

CREATE VIEW get_authors AS
  SELECT author.author_name
  FROM author

  ORDER BY author.author_name;

CREATE VIEW get_books_in_library AS
  SELECT
    book.title                  AS title,
    library.library_name        AS library_name,
    library_book.nubmer_of_book AS number
  FROM book, library_book, library
  WHERE book.book_id = library_book.book_id
        AND library_book.library_id = library.library_id
  ORDER BY (book.title, library.library_name);

CREATE VIEW get_books_number_in_category  AS
  SELECT
    category_name                    AS category_name,
    sum(library_book.nubmer_of_book) AS numberOfBook
  FROM category, book_category, book, library_book
  WHERE category.category_id = book_category.category_id
        AND book.book_id = book_category.book_id
        AND book.book_id = library_book.book_id
  GROUP BY category.category_id

CREATE VIEW get_books_number_in_library  AS
  SELECT library_name AS library_name,
         sum(library_book.nubmer_of_book) AS numberOfBook
  FROM library, book, library_book
  WHERE book.book_id = library_book.book_id
        AND library.library_id = library_book.library_id
  GROUP BY library.library_id



