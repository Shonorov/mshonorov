CREATE DATABASE tracker_db
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Russian_Russia.1251'
    LC_CTYPE = 'Russian_Russia.1251'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

----------------------CREATE TABLES----------------------------------------------
CREATE TABLE public.rule
(
    "NAME" text NOT NULL,
    "ID" serial NOT NULL,
    "DESC" text,
    "ACCESS" integer NOT NULL,
    PRIMARY KEY ("ID")
)
WITH (
    OIDS = FALSE
);

ALTER TABLE public.rule
    OWNER to postgres;
----------------------------	
CREATE TABLE public.role
(
    "NAME" text NOT NULL,
    "ID" serial NOT NULL,
    "DESC" text,
    "SCOPE" text NOT NULL,
    PRIMARY KEY ("ID")
)
WITH (
    OIDS = FALSE
);

ALTER TABLE public.role
    OWNER to postgres;	
---------------------------	
CREATE TABLE public.role_rules
(
    "roleID" integer NOT NULL,
    "ID" serial NOT NULL,
    "ruleID" integer NOT NULL,
	  "timestamp" timestamp default current_timestamp,
    PRIMARY KEY ("ID"),
    CONSTRAINT "roleID" FOREIGN KEY ("roleID")
        REFERENCES public.role ("ID") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "ruleID" FOREIGN KEY ("ruleID")
        REFERENCES public.rule ("ID") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
);

ALTER TABLE public.role_rules
    OWNER to postgres;
---------------------------
CREATE TABLE public.users
(
    "ID" serial NOT NULL,
    name text NOT NULL,
    "roleID" integer NOT NULL,
    email text,
    phone text,
    PRIMARY KEY ("ID"),
    CONSTRAINT "roleID" FOREIGN KEY ("roleID")
        REFERENCES public.role ("ID") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
);

ALTER TABLE public.users
    OWNER to postgres;
---------------------------
CREATE TABLE public.item
(
    "ID" serial NOT NULL,
    header text,
    body text,
    timestamp timestamp default current_timestamp,
    PRIMARY KEY ("ID")
)
WITH (
    OIDS = FALSE
);

ALTER TABLE public.item
    OWNER to postgres;
---------------------------	
CREATE TABLE public.user_items
(
    "ID" serial NOT NULL,
    "userID" integer NOT NULL,
    "itemID" integer NOT NULL,
    "userRole" text,
    PRIMARY KEY ("ID"),
    CONSTRAINT "userID" FOREIGN KEY ("userID")
        REFERENCES public.users ("ID") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "itemID" FOREIGN KEY ("itemID")
        REFERENCES public.item ("ID") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
);

ALTER TABLE public.user_items
    OWNER to postgres;
---------------------------
CREATE TABLE public.state
(
    "ID" serial NOT NULL,
    "itemID" integer NOT NULL,
    name text NOT NULL,
    "desc" text,
    PRIMARY KEY ("ID"),
    CONSTRAINT "itemID" FOREIGN KEY ("itemID")
        REFERENCES public.item ("ID") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
);

ALTER TABLE public.state
    OWNER to postgres;
---------------------------
CREATE TABLE public.comments
(
    "ID" serial NOT NULL,
    "itemID" integer NOT NULL,
    comment text,
    "timestamp" timestamp without time zone DEFAULT current_timestamp,
    PRIMARY KEY ("ID"),
    CONSTRAINT "itemID" FOREIGN KEY ("itemID")
        REFERENCES public.item ("ID") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
);

ALTER TABLE public.comments
    OWNER to postgres;
---------------------------
CREATE TABLE public.category
(
    "ID" serial NOT NULL,
    "itemID" integer NOT NULL,
    name text NOT NULL,
    "desc" text,
    PRIMARY KEY ("ID"),
    CONSTRAINT "itemID" FOREIGN KEY ("itemID")
        REFERENCES public.item ("ID") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
);

ALTER TABLE public.category
    OWNER to postgres;
---------------------------
CREATE TABLE public.attachments
(
    "ID" serial NOT NULL,
    "itemID" integer NOT NULL,
    path TEXT NOT NULL,
    PRIMARY KEY ("ID"),
    CONSTRAINT "itemID" FOREIGN KEY ("itemID")
        REFERENCES public.item ("ID") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
);

ALTER TABLE public.attachments
    OWNER to postgres;
-----------------------------------------FILL DATA------------------------------------------------
INSERT INTO public.role ("ID", "NAME", "SCOPE", "DESC") VALUES
  (DEFAULT, 'Administrator', 'Server', 'Server full access'),
	 (DEFAULT, 'Read-Only', 'Server', 'Server read access'),
	 (DEFAULT, 'DBOwner', 'Database', 'Database full access'),
	 (DEFAULT, 'Author', 'Database', 'Database modifier'),
	 (DEFAULT, 'User', 'Database', 'Database read access');
---------------------------
INSERT INTO public.rule("NAME", "ID", "DESC", "ACCESS") VALUES
	('READ', DEFAULT, 'Read access', 0),
	('WRITE', DEFAULT, 'Write access', 1),
	('MODIFY', DEFAULT, 'Modify access', 2),
	('FULL', DEFAULT, 'Full access', 3);
---------------------------
INSERT INTO public.role_rules("ID", "roleID", "ruleID", "timestamp") VALUES
	(DEFAULT, 4, 2, CURRENT_TIMESTAMP),
	(DEFAULT, 2, 1, CURRENT_TIMESTAMP),
	(DEFAULT, 3, 4, CURRENT_TIMESTAMP),
	(DEFAULT, 4, 3, CURRENT_TIMESTAMP),
	(DEFAULT, 5, 1, CURRENT_TIMESTAMP);
---------------------------
INSERT INTO public.users("ID", name, "roleID", email, phone) VALUES
	(DEFAULT, 'root', 1, 'root@tracker.com', '+79161111111'),
	(DEFAULT, 'reader', 2, 'reader@tracker.com', '+79162222222'),
	(DEFAULT, 'db_master', 3, 'db_master@tracker.com', '+79163333333'),
	(DEFAULT, 'author', 4, 'author@tracker.com', '+79164444444'),
	(DEFAULT, 'user1', 5, 'user1@tracker.com', '+79165555555');
---------------------------
INSERT INTO public.item("ID", header, body, "timestamp") VALUES
	(DEFAULT, 'ticket1', 'problem1', CURRENT_TIMESTAMP),
	(DEFAULT, 'ticket2', 'problem2', CURRENT_TIMESTAMP),
	(DEFAULT, 'ticket3', 'problem3', CURRENT_TIMESTAMP),
	(DEFAULT, 'ticket4', 'problem4', CURRENT_TIMESTAMP),
	(DEFAULT, 'ticket5', 'problem5', CURRENT_TIMESTAMP);
---------------------------
INSERT INTO public.user_items("ID", "userID", "itemID", "userRole") VALUES
	(DEFAULT, 1, 1, 'Specialist'),
	(DEFAULT, 2, 2, 'Author'),
	(DEFAULT, 3, 3, 'Manager'),
	(DEFAULT, 4, 4, 'Specialist'),
	(DEFAULT, 5, 5, 'Author');
---------------------------
INSERT INTO public.state("ID", "itemID", "name", "desc") VALUES
	(DEFAULT, 1, 'New', 'New item'),
	(DEFAULT, 2, 'In progress', 'Doing task'),
	(DEFAULT, 3, 'Finished', 'Completed task'),
	(DEFAULT, 4, 'Closed', 'Closed task'),
	(DEFAULT, 5, 'Archived', 'Archived task');
---------------------------
INSERT INTO public.attachments("ID", "itemID", "path") VALUES
	(DEFAULT, 1, 'D:\scripts\file11.txt'),
	(DEFAULT, 1, 'D:\scripts\file12.txt'),
	(DEFAULT, 3, 'D:\scripts\file31.txt'),
	(DEFAULT, 5, 'D:\scripts\file51.txt'),
	(DEFAULT, 5, 'D:\scripts\file52.txt'),
	(DEFAULT, 5, 'D:\scripts\file53.txt');
---------------------------
INSERT INTO public.category("ID", "itemID", "name", "desc") VALUES
	(DEFAULT, 1, 'Urgent', 'High Priority'),
	(DEFAULT, 2, 'Critical', 'Deadline'),
	(DEFAULT, 3, 'Normal', 'Standard priority'),
	(DEFAULT, 4, 'Waiting', 'Paused task'),
	(DEFAULT, 5, 'Low', 'Low priority');
---------------------------
INSERT INTO public.comments("ID", "itemID", "comment", "timestamp") VALUES
	(DEFAULT, 1, 'Comment11', CURRENT_TIMESTAMP),
	(DEFAULT, 1, 'Comment12', CURRENT_TIMESTAMP),
	(DEFAULT, 3, 'Comment31', CURRENT_TIMESTAMP),
	(DEFAULT, 5, 'Comment51', CURRENT_TIMESTAMP),
	(DEFAULT, 5, 'Comment51', CURRENT_TIMESTAMP),
	(DEFAULT, 5, 'Comment51', CURRENT_TIMESTAMP);
---------------------------	
