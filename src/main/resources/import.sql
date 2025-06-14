-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;


insert into moviedirector (id, name)
values 
(1, 'David Finch'),
(2, 'Darren Aronofsky'),
(3, 'Denis Villeneuve'),
(4, 'Christopher Nolan'),
(5, 'Martin Scorsese'),
(6, 'Quentin Tarantino')
;

insert into movie (id, director_id, genre, averagerating, releaseyear, title)
values 
(1, 1, 'THRILLER', 8.6, 1995, 'Os Sete Crimes Capitais'),
(2, 1, 'THRILLER', 8.1, 2014, 'Garota Exemplar'),
(3, 1, 'DRAMA', 7.8, 2010, 'A Rede Social'),
(4, 1, 'DRAMA', 7.8, 2009, 'O Curioso Caso de Benjamin Button'),
(5, 2, 'DRAMA', 8.0, 2011, 'Cisne Negro'),
(6, 2, 'DRAMA', 7.6, 2022, 'A Baleia'),
(7, 3, 'SCI_FI', 8.5, 2024, 'Duna: Parte 2'),
(8, 3, 'SCI_FI', 7.9, 2016, 'A Chegada'),
(9, 3, 'THRILLER', 8.2, 2013, 'Os Suspeitos'),
(10, 4, 'DRAMA', 8.3, 2023, 'Oppenheimer'),
(11, 4, 'SCI_FI', 8.7, 2014, 'Interestelar'),
(12, 4, 'ACTION', 8.8, 2010, 'A Origem'),
(13, 4, 'ACTION', 9.0, 2008, 'Batman: O Cavaleiro das Trevas'),
(14, 5, 'DRAMA', 8.5, 2006, 'Os Infiltrados'),
(15, 5, 'COMEDY', 8.2, 2013, 'O Lobo de Wall Street'),
(16, 5, 'THRILLER', 8.2, 2010, 'Ilha do Medo'),
(17, 6, 'ACTION', 8.2, 2003, 'Kill Bill: Volume 1'),
(18, 6, 'ACTION', 8.5, 2012, 'Django Livre'),
(19, 6, 'DRAMA', 8.4, 2009, 'Bastardos Inglórios')
;

insert into users(id, name, email, password)
values
(1, 'Samuel', 'samuel@email.com', '12345'),
(2, 'João', 'joao@email.com', '12345');


