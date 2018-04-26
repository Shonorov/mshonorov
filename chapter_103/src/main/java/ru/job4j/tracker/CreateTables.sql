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