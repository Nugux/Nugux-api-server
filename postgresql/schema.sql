CREATE TABLE public.tourist_spots
(
    id integer NOT NULL DEFAULT nextval('tourist_spots_id_seq'::regclass),
    name character varying COLLATE pg_catalog."default" NOT NULL,
    address character varying COLLATE pg_catalog."default",
    postal_code character varying COLLATE pg_catalog."default",
    description character varying COLLATE pg_catalog."default",
    lat double precision NOT NULL,
    "long" double precision NOT NULL,
    CONSTRAINT tourist_spots_pkey PRIMARY KEY (id)
)