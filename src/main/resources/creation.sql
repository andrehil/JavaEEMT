CREATE SCHEMA fred
  AUTHORIZATION postgres;

CREATE SCHEMA joe
  AUTHORIZATION postgres;
  
set schema 'fred';

CREATE TABLE car
(
  id bigint NOT NULL,
  brand character varying(30),
  name character varying(30),
  CONSTRAINT pk_car PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE car
  OWNER TO postgres;

CREATE SEQUENCE fred.car_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE fred.car_seq
  OWNER TO postgres;

set schema 'joe';

CREATE TABLE car
(
  id bigint NOT NULL,
  brand character varying(30),
  name character varying(30),
  CONSTRAINT pk_car PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE car
  OWNER TO postgres;

CREATE SEQUENCE joe.car_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE joe.car_seq
  OWNER TO postgres;
