CREATE TABLE voter (
	id serial PRIMARY KEY,
	registration_date date NOT NULL,
	name varchar(255) NOT NULL,
	password varchar(255) NOT NULL,
	login varchar(255) NOT NULL,
	phone varchar(11) UNIQUE
);

CREATE TABLE poll(
	id serial PRIMARY KEY,
	title varchar(255) NOT NULL,
	visible boolean NOT NULL,
	date_to date NOT NULL,
	start_date date NOT NULL CHECK (date_to < start_date ),
	create_date date NOT NULL
);

CREATE TABLE question(
	id serial PRIMARY KEY,
	poll_id int NOT NULL REFERENCES poll(id) ON DELETE CASCADE,
	question varchar(255) NOT NULL,
	created_date date NOT NULL
);

CREATE TABLE variants(
	id serial PRIMARY KEY,
	question_id int NOT NULL REFERENCES question(id) ON DELETE CASCADE,
	text varchar(255) NOT NULL
);

CREATE TABLE answer(
	id serial PRIMARY KEY,
	voter_id int NOT NULL REFERENCES voter(id) ON DELETE CASCADE,
	variant_id int NOT NULL REFERENCES variants(id) ON DELETE CASCADE,
	answer_date date NOT NULL
);


INSERT INTO voter
VALUES (1,'11.11.2019', 'Ivan', '1720e61cd37718a479b0ff153d374aed', '89171231234'),
       (2,'11.10.2019', 'Serge', '74ce383083e38d12007cdffcd6fe5448', '89174561234'),
       (3,'10.10.2019', 'Nikolay', '6f1a41ff6dae02f091f5db87223b080e', '89674561234'),
       (4,'10.10.2019', 'Olga', '29069193701fce7e4a7a121cc3a70867', '89674561534');

INSERT INTO poll
VALUES (1, 'Опрос о реконструкции парка.', true, '10.10.2019', '12.10.2019', '11.10.2019');



INSERT INTO question
VALUES (1, 1, 'Что нужно улучшить?', '11.10.2019'),
       (2, 1, 'Сколько нужно потратить $ ?', '11.10.2019');

INSERT INTO variants VALUES
(1, 1, 'Стадион'),
(2, 1, 'Дорожки'),
(3, 1, 'Лесопосадку'),
(4, 2, '1000$'),
(5, 2, '10000$'),
(6, 2, '100000$');

INSERT INTO answer VALUES
(1, 1, 1, '12.10.2019'),
(2, 2, 1, '11.10.2019')

