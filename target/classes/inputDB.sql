INSERT INTO publisher (publisher_name) VALUES ('КоЛибри');
INSERT INTO publisher (publisher_name) VALUES ('Азбука');
INSERT INTO publisher (publisher_name) VALUES ('Астрель');
INSERT INTO publisher (publisher_name) VALUES ('Еврокарта');

INSERT INTO author (author_name) VALUES ('Стивен Кинг');
INSERT INTO author (author_name) VALUES ('Рэй Брэдбери');
INSERT INTO author (author_name) VALUES ('Михаил Булгаков');
INSERT INTO author (author_name) VALUES ('Александр Пушкин');
INSERT INTO author (author_name) VALUES ('Эдуард Успенский');
INSERT INTO author (author_name) VALUES ('Джоанна Бэсфорд');
INSERT INTO author (author_name) VALUES ('Карл Маркс');
INSERT INTO author (author_name) VALUES ('Стив Макконнелл');
INSERT INTO author (author_name) VALUES ('Ричард Докинз');
INSERT INTO author (author_name) VALUES ('Иммануил Кант');
INSERT INTO author (author_name) VALUES ('Аркадий Стругацкий');
INSERT INTO author (author_name) VALUES ('Борис Стругацкий');
INSERT INTO author (author_name) VALUES ('Анна Рапопорт');


INSERT INTO category (category_name) VALUES ('Художественная литература');
INSERT INTO category (category_name) VALUES ('Научная литература');
INSERT INTO category (category_name) VALUES ('Компьютерная литература');
INSERT INTO category (category_name) VALUES ('Бизнес, экономика и право');
INSERT INTO category (category_name) VALUES ('Искусство');
INSERT INTO category (category_name) VALUES ('Краеведение');
INSERT INTO category (category_name) VALUES ('Литература для детей');

INSERT INTO library (library_name, adress) VALUES ('На Стремянной', 'ул. Стремянная, 20');
INSERT INTO library (library_name, adress) VALUES ('М-86','пр. Московский, д. 86');


INSERT INTO book (title, description,year, publisher_id) VALUES ('Мастер и Маргарита','Великий и вечный роман писателя претерпел многочисленные запреты к изданию и гонения, не хуже чем судьба романа о Понтии Пилате самого Мастера.',2001,1);
INSERT INTO book (title, description, year, publisher_id) VALUES ('Вино из одуванчиков','Войдите в светлый мир двенадцатилетнего мальчика и проживите вместе с ним одно лето, наполненное событиями радостными печальными, загадочными и тревожными; лето, когда каждый день совершаются удивительные открытия, главное из которых - ты живой, ты дышишь, ты чувствуешь! ' ,2004, 1);
INSERT INTO book (title, description, year, publisher_id) VALUES ('Марсианские хроники','Марсианские хроники : странный изменчивый мир, населенный загадочными, неуловимыми обитателями...',2012,2);
INSERT INTO book (title, description, year, publisher_id) VALUES ('Сияние','Из роскошного отеля выезжают на зиму все... кроме призраков, и самые невообразимые кошмары становятся явью. Черный, как полночь, ужас всю зиму царит в занесенном снегами, отрезанном от мира отеле.',1998, 2);
INSERT INTO book (title, description, year, publisher_id) VALUES ('Зеленая миля','Ничто из того, что вы читали раньше, не сравнится с самым дерзким из ужасных опытов Стивена Кинга — с историей, что начинается на Дороге Смерти и уходит в глубины самых чудовищных тайн человеческой души...',2001,2);
INSERT INTO book (title, description, year, publisher_id) VALUES ('Дядя Фёдор, пёс и кот','',2005,3);
INSERT INTO book (title, description, year, publisher_id) VALUES ('Евгений Онегин',' В сборник вошли роман в стихах ''Евгений Онегин'', поэмы, драмы и сказки Пушкина.',1987,1);
INSERT INTO book (title, description, year, publisher_id) VALUES ('Капитанская дочка','Оригинальное издание, в которое включен адаптированный для детей среднего школьного возраста вариант повести Александра Сергеевича Пушкина «Капитанская дочка»',2017,3);
INSERT INTO book (title, description, year, publisher_id) VALUES ('Сказка о царе Салтане','',1988,3);
INSERT INTO book (title, description, year, publisher_id) VALUES ('Критика чистого ума','«Критика чистого разума» – книга, которую каждая эпоха заново оценивает и по-своему переосмысливает. Книга, которая остается актуальной вот уже более двух столетий. ',2007,2);
INSERT INTO book (title, description, year, publisher_id) VALUES ('Бог как иллюзия','Ричард Докинз — выдающийся британский ученый-этолог и популяризатор науки, лауреат многих литературных и научных премий. В этой книге он проявляет талант блестящего полемиста, обращаясь к острейшим и актуальнейшим проблемам современного мира.',2011,1);
INSERT INTO book (title, description, year, publisher_id) VALUES ('Совершенный код','Более 10 лет первое издание этой книги считалось одним из лучших практических руководств по программированию. Сейчас эта книга полностью обновлена с учетом современных тенденций и технологий и дополнена сотнями новых примеров, иллюстрирующих искусство и науку программирования.',2001,1);
INSERT INTO book (title, description, year, publisher_id) VALUES ('Капитал','',2005,2);
INSERT INTO book (title, description, year, publisher_id) VALUES ('Рождественские чудеса','Все мы стараемся оставаться детьми. Хотя бы чуть-чуть. И верим в чудеса. Хотя бы немного. И пытаемся их творить. Хотя бы иногда',2014,2);
INSERT INTO book (title, description, year, publisher_id) VALUES ('Карта Санкт-Петербург + окрестности','Сложенная карта «Карта Санкт-Петербург + окрестности», масштаб: Санкт-Петербург 1:35 000, центр города 1:22 000. На карте: Кронштадт, Петергоф, Ломоносов, Стрельна, Пушкин, Павловск, указатель улиц, справочная информация.',2009,4);
INSERT INTO book (title, description, year, publisher_id) VALUES ('Трудно быть богом','Возможно, самое известное из произведений братьев Стругацких. Один из самых прославленных романов отечественной фантастики.',2015,2);

INSERT INTO book_category (book_id, category_id) VALUES (1,1);
INSERT INTO book_category (book_id, category_id) VALUES (2,1);
INSERT INTO book_category (book_id, category_id) VALUES (3,1);
INSERT INTO book_category (book_id, category_id) VALUES (4,1);
INSERT INTO book_category (book_id, category_id) VALUES (5,1);
INSERT INTO book_category (book_id, category_id) VALUES (6,7);
INSERT INTO book_category (book_id, category_id) VALUES (7,1);
INSERT INTO book_category (book_id, category_id) VALUES (8,7);
INSERT INTO book_category (book_id, category_id) VALUES (9,7);
INSERT INTO book_category (book_id, category_id) VALUES (10,2);
INSERT INTO book_category (book_id, category_id) VALUES (11,2);
INSERT INTO book_category (book_id, category_id) VALUES (12,3);
INSERT INTO book_category (book_id, category_id) VALUES (13,4);
INSERT INTO book_category (book_id, category_id) VALUES (14,5);
INSERT INTO book_category (book_id, category_id) VALUES (15,6);
INSERT INTO book_category (book_id, category_id) VALUES (16,1);

INSERT INTO book_author (book_id, author_id) VALUES (1,3);
INSERT INTO book_author (book_id, author_id) VALUES (2,2);
INSERT INTO book_author (book_id, author_id) VALUES (3,2);
INSERT INTO book_author (book_id, author_id) VALUES (4,1);
INSERT INTO book_author (book_id, author_id) VALUES (5,1);
INSERT INTO book_author (book_id, author_id) VALUES (6,5);
INSERT INTO book_author (book_id, author_id) VALUES (7,4);
INSERT INTO book_author (book_id, author_id) VALUES (8,4);
INSERT INTO book_author (book_id, author_id) VALUES (9,4);
INSERT INTO book_author (book_id, author_id) VALUES (10,10);
INSERT INTO book_author (book_id, author_id) VALUES (11,9);
INSERT INTO book_author (book_id, author_id) VALUES (12,8);
INSERT INTO book_author (book_id, author_id) VALUES (13,7);
INSERT INTO book_author (book_id, author_id) VALUES (14,6);
INSERT INTO book_author (book_id, author_id) VALUES (15,13);
INSERT INTO book_author (book_id, author_id) VALUES (16,11);
INSERT INTO book_author (book_id, author_id) VALUES (16,12);

INSERT INTO library_book (library_id, book_id, nubmer_of_book) VALUES (1,1,3);
INSERT INTO library_book (library_id, book_id, nubmer_of_book) VALUES (1,2,1);
INSERT INTO library_book (library_id, book_id, nubmer_of_book) VALUES (1,5,2);
INSERT INTO library_book (library_id, book_id, nubmer_of_book) VALUES (1,3,4);
INSERT INTO library_book (library_id, book_id, nubmer_of_book) VALUES (1,6,4);
INSERT INTO library_book (library_id, book_id, nubmer_of_book) VALUES (1,8,1);
INSERT INTO library_book (library_id, book_id, nubmer_of_book) VALUES (1,9,3);
INSERT INTO library_book (library_id, book_id, nubmer_of_book) VALUES (1,10 ,6);
INSERT INTO library_book (library_id, book_id, nubmer_of_book) VALUES (1,13,6);
INSERT INTO library_book (library_id, book_id, nubmer_of_book) VALUES (1,14,2);
INSERT INTO library_book (library_id, book_id, nubmer_of_book) VALUES (2,2,3);
INSERT INTO library_book (library_id, book_id, nubmer_of_book) VALUES (2,4,4);
INSERT INTO library_book (library_id, book_id, nubmer_of_book) VALUES (2,7,2);
INSERT INTO library_book (library_id, book_id, nubmer_of_book) VALUES (2,8,4);
INSERT INTO library_book (library_id, book_id, nubmer_of_book) VALUES (2,11,3);
INSERT INTO library_book (library_id, book_id, nubmer_of_book) VALUES (2,14,4);
INSERT INTO library_book (library_id, book_id, nubmer_of_book) VALUES (2,15,3);
INSERT INTO library_book (library_id, book_id, nubmer_of_book) VALUES (2,12,4);
INSERT INTO library_book (library_id, book_id, nubmer_of_book) VALUES (2,16,1);
INSERT INTO library_book (library_id, book_id, nubmer_of_book) VALUES (1,16,1);