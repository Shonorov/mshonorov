CREATE TABLE company
(
id integer NOT NULL,
name character varying,
CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
id integer NOT NULL,
name character varying,
company_id integer,
CONSTRAINT person_pkey PRIMARY KEY (id)
);

INSERT INTO public.company(
	id, name) VALUES 
	(1, 'Company1'),
	(2, 'Company2'),
	(3, 'Company3'),
	(4, 'Company4'),
	(5, 'Company5');
	
INSERT INTO public.person(
	id, name, company_id) VALUES 
	(1, 'person1', 1),
	(2, 'person2', 2),
	(3, 'person3', 3),
	(4, 'person4', 5),
	(5, 'person5', 1),
	(6, 'person6', 2),
	(7, 'person7', 5),
	(8, 'person8', 4),
	(9, 'person8', 2);
	
-- 1) Retrieve in a single query:
-- names of all persons that are NOT in the company with id = 5
-- company name for each person

SELECT p.name AS "Person", c.name AS "Company" FROM person AS p
INNER JOIN company AS c
ON p.company_id = c.id
WHERE p.company_id != 5;

-- 2) Select the name of the company with the maximum number of persons + number of persons in this company

WITH summ AS (SELECT c.name AS Company, COUNT(*) AS Count FROM company AS c
INNER JOIN person AS p
ON c.id = p.company_id
GROUP BY c.name)
SELECT * FROM summ
WHERE count = (SELECT max(count) FROM summ);
