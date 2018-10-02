INSERT INTO BOOK (TITLE) VALUES ('book-3');
SET @book1 = LAST_INSERT_ID();
INSERT INTO BOOK (TITLE) VALUES ('book-4');
SET @book2 = LAST_INSERT_ID();
insert into customer(firstname, lastname, customerType) values('john', 'hitler', 'GOLD');
insert into customer(firstname, lastname, customerType) values('niels', 'peter', 'SILVER');
insert into customer(firstname, lastname, customerType) values('ruben', 'hansen', 'SILVER');
insert into customer(firstname, lastname, customerType) values('flemming', 'johansen', 'RUSTY');
insert into customer(firstname, lastname, customerType) values('svenning', 'dalgaard', 'IRON');




