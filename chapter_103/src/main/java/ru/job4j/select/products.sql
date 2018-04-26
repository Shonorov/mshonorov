CREATE TABLE public.type
(
    id serial NOT NULL,
    name text NOT NULL,
    PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
);

ALTER TABLE public.type
    OWNER to postgres;

CREATE TABLE public.product
(
    id serial NOT NULL,
    name text NOT NULL,
    type_id integer NOT NULL,
    expired_date date NOT NULL,
    price double precision NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT type_id FOREIGN KEY (type_id)
        REFERENCES public.type (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
);

ALTER TABLE public.product
    OWNER to postgres;


INSERT INTO public.type(
	id, "name") VALUES 
	(DEFAULT, 'СЫР'),
	(DEFAULT, 'МОЛОКО'),
	(DEFAULT, 'МЯСО'),
	(DEFAULT, 'ХЛЕБ');
	
INSERT INTO public.product(
	id, "name", "type_id", "expired_date", "price") VALUES 	
	(DEFAULT, 'Сыр1', 1, '15 Mar 2018', 100.0),
	(DEFAULT, 'Сыр2', 1, '16 Mar 2018', 200.0),
	(DEFAULT, 'Сыр3', 1, '17 Mar 2018', 300.0),
	(DEFAULT, 'Молоко1', 2, '16 Apr 2018', 150.0),
	(DEFAULT, 'Молоко2', 2, '17 Apr 2018', 250.0),
	(DEFAULT, 'Молоко3', 2, '18 Apr 2018', 350.0),
	(DEFAULT, 'Мясо1', 3, '19 May 2018', 170.0),
	(DEFAULT, 'Мясо2', 3, '20 May 2018', 270.0),
	(DEFAULT, 'Мясо3', 3, '21 May 2018', 370.0),
	(DEFAULT, 'Хлеб1', 4, '22 Jun 2018', 60.0);
	
--Написать запрос получение всех продуктов с типом "СЫР"
	SELECT * FROM product as p
	INNER JOIN type as t
	ON p.type_id = t.id
	where t.name = 'СЫР';
--Написать запрос получения всех продуктов, у кого в имени есть слово "мясо"	
	SELECT * FROM product
	WHERE name LIKE '%Мясо%';
--Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
	SELECT * FROM product
	WHERE expired_date BETWEEN '01 May 2018' AND '31 May 2018';
--Написать запрос, который вывод самый дорогой продукт.	
	SELECT * FROM product
	WHERE price = (SELECT MAX(price) FROM product);
--Написать запрос, который выводит количество всех продуктов определенного типа.
	SELECT t.name, COUNT(type_id) FROM product as p 
	INNER JOIN type as t
	ON p.type_id = t.id
	WHERE t.name = 'СЫР'
	GROUP BY t.name;
--Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"	
	SELECT * FROM product as p
	INNER JOIN type as t
	ON p.type_id = t.id
	where t.name IN ('СЫР','МОЛОКО');
--Написать запрос, который выводит тип продуктов, которых осталось меньше 2 штук.  	
	SELECT t.name, COUNT(type_id) FROM product as p
	INNER JOIN type as t
	ON p.type_id = t.id
	GROUP BY t.name
	HAVING COUNT(type_id) < 2;
--Вывести все продукты и их тип.	
	SELECT p.name, t.name FROM product as p
	INNER JOIN type as t
	ON p.type_id = t.id;
	
	select * from product
	