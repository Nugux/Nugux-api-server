create table tourist_spots
(
    id serial not null
        constraint tourist_spots_pkey
            primary key,
    name varchar not null,
    address varchar,
    postal_code varchar,
    description varchar,
    lat double precision not null,
    long double precision not null,
    congestion double precision default 0 not null,
	premium boolean default false
);

alter table tourist_spots owner to postgres;

create table daily_spot_congestions
(
    id serial not null
        constraint daily_spot_congestions_pkey
            primary key,
    state varchar not null,
    city varchar,
    congestion double precision not null,
    spot_level varchar not null,
    update_date date not null,
    lat double precision default 0 not null,
    long double precision default 0 not null,
    constraint unique_state_city_congestion_key
        unique (state, city, spot_level)
);

alter table daily_spot_congestions owner to postgres;

create table spot_congestions
(
    id serial not null
        constraint spot_congestions_pkey
            primary key,
    spot_id integer,
    congestion double precision not null,
    day integer
);

alter table spot_congestions owner to postgres;

