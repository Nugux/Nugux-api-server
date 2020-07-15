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

CREATE TABLE public.daily_spot_congestions
(
    id integer NOT NULL DEFAULT nextval('daily_spot_congestions_id_seq'::regclass),
    state character varying COLLATE pg_catalog."default" NOT NULL,
    city character varying COLLATE pg_catalog."default",
    congestion double precision NOT NULL,
    spot_level character varying COLLATE pg_catalog."default" NOT NULL,
    update_date date NOT NULL,
    CONSTRAINT daily_spot_congestions_pkey PRIMARY KEY (id),
    CONSTRAINT unique_state_city_congestion_key UNIQUE (state, city, spot_level)
)