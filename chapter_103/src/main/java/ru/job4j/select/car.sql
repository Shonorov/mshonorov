CREATE TABLE public.engine
(
    id integer NOT NULL DEFAULT nextval('engine_id_seq'::regclass),
    model text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT engine_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.engine
    OWNER to postgres;
	
CREATE TABLE public.gearbox
(
    id integer NOT NULL DEFAULT nextval('gearbox_id_seq'::regclass),
    model text COLLATE pg_catalog."default",
    CONSTRAINT gearbox_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.gearbox
    OWNER to postgres;	
	
CREATE TABLE public.transmission
(
    id integer NOT NULL DEFAULT nextval('transmissions_id_seq'::regclass),
    model text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT transmissions_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.transmission
    OWNER to postgres;
	
CREATE TABLE public.car
(
    id integer NOT NULL DEFAULT nextval('car_id_seq'::regclass),
    model text COLLATE pg_catalog."default" NOT NULL,
    transmission_id integer NOT NULL,
    engine_id integer NOT NULL,
    gearbox_id integer NOT NULL,
    CONSTRAINT car_pkey PRIMARY KEY (id),
    CONSTRAINT engine_id FOREIGN KEY (engine_id)
        REFERENCES public.engine (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT gearbox_id FOREIGN KEY (gearbox_id)
        REFERENCES public.gearbox (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT transmission_id FOREIGN KEY (transmission_id)
        REFERENCES public.transmission (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.car
    OWNER to postgres;
------------------------------------------------------------------------------------------
INSERT INTO public.engine(id, model) VALUES 
	(DEFAULT, 'engine1'),
	(DEFAULT, 'engine2'),
	(DEFAULT, 'engine3');
	
INSERT INTO public.gearbox(id, model) VALUES 
	(DEFAULT, 'gearbox1'),
	(DEFAULT, 'gearbox2'),
	(DEFAULT, 'gearbox3');
	
INSERT INTO public.transmission(id, model) VALUES 
	(DEFAULT, 'transmission1'),
	(DEFAULT, 'transmission2'),
	(DEFAULT, 'transmission3');
	
INSERT INTO public.car(id, model, transmission_id, engine_id, gearbox_id) VALUES 
	(DEFAULT, 'car1', 1, 1, 1),
	(DEFAULT, 'car2', 2, 2, 2);
------------------------------------------------------------------------------------------
--Вывести все машины.
SELECT c.model, t.model, g.model, e.model FROM car AS c
LEFT JOIN transmission AS t
ON transmission_id = t.id
LEFT JOIN gearbox AS g
ON gearbox_id = g.id
LEFT JOIN engine AS e
ON engine_id = e.id;
--Вывести все неиспользуемые детали.
SELECT t.model, g.model, e.model FROM car AS c
FULL JOIN transmission AS t
ON transmission_id = t.id
FULL JOIN gearbox AS g
ON gearbox_id = g.id
FULL JOIN engine AS e
ON engine_id = e.id
WHERE c.model IS null;
