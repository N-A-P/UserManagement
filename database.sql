
/* Drop Tables */

DROP TABLE IF EXISTS campaign_customer;
DROP TABLE IF EXISTS campaign;
DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS user_department;
DROP TABLE IF EXISTS department;
DROP TABLE IF EXISTS email_template;
DROP TABLE IF EXISTS role_pages;
DROP TABLE IF EXISTS pages;
DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS users;



/* Drop Sequences */

DROP SEQUENCE IF EXISTS campaign_customer_id_seq;
DROP SEQUENCE IF EXISTS campaign_id_seq;
DROP SEQUENCE IF EXISTS customer_id_seq;
DROP SEQUENCE IF EXISTS department_id_seq;
DROP SEQUENCE IF EXISTS email_template_id_seq;
DROP SEQUENCE IF EXISTS page_id_seq;
DROP SEQUENCE IF EXISTS role_id_seq;
DROP SEQUENCE IF EXISTS role_page_id_seq;
DROP SEQUENCE IF EXISTS user_department_id_seq;
DROP SEQUENCE IF EXISTS user_id_seq;
DROP SEQUENCE IF EXISTS user_role_id_seq;




/* Create Sequences */

CREATE SEQUENCE campaign_customer_id_seq INCREMENT 1 MINVALUE 1 START 1;
CREATE SEQUENCE campaign_id_seq INCREMENT 1 MINVALUE 1 START 1;
CREATE SEQUENCE customer_id_seq INCREMENT 1 MINVALUE 1 START 1;
CREATE SEQUENCE department_id_seq INCREMENT 1 MINVALUE 1 START 1;
CREATE SEQUENCE email_template_id_seq INCREMENT 1 MINVALUE 1 START 1;
CREATE SEQUENCE page_id_seq INCREMENT 1 MINVALUE 1 START 1;
CREATE SEQUENCE role_id_seq INCREMENT 1 MINVALUE 1 START 1;
CREATE SEQUENCE role_page_id_seq INCREMENT 1 MINVALUE 1 START 1;
CREATE SEQUENCE user_department_id_seq INCREMENT 1 MINVALUE 1 START 1;
CREATE SEQUENCE user_id_seq INCREMENT 1 MINVALUE 1 START 1;
CREATE SEQUENCE user_role_id_seq INCREMENT 1 MINVALUE 1 START 1;



/* Create Tables */

CREATE TABLE campaign
(
	id bigint DEFAULT nextval('campaign_id_seq') NOT NULL UNIQUE,
	title varchar(50) NOT NULL,
	description varchar(1000),
	duration int,
	start_date date,
	end_date date,
	is_activated int,
	updated_by varchar(50),
	created_timestamp timestamp with time zone,
	updated_timestamp timestamp with time zone,
	email_tempale_id bigint NOT NULL,
	PRIMARY KEY (id)
) WITHOUT OIDS;


CREATE TABLE campaign_customer
(
	id bigint DEFAULT nextval('campaign_customer_id_seq') NOT NULL UNIQUE,
	sent_date date NOT NULL,
	updated_by varchar(50),
	created_timestamp timestamp with time zone,
	updated_timestamp timestamp with time zone,
	campaign_id bigint NOT NULL,
	customer_id bigint NOT NULL,
	user_id bigint NOT NULL,
	PRIMARY KEY (id)
) WITHOUT OIDS;


CREATE TABLE customer
(
	id bigint DEFAULT nextval('customer_id_seq') NOT NULL UNIQUE,
	first_name varchar(50) NOT NULL,
	last_name varchar(50) NOT NULL,
	dob date,
	email varchar(50) NOT NULL UNIQUE,
	address varchar(200),
	company varchar(50),
	is_activated int,
	updated_by varchar(50),
	created_timestamp timestamp with time zone,
	updated_timestamp timestamp with time zone,
	PRIMARY KEY (id)
) WITHOUT OIDS;


CREATE TABLE department
(
	id bigint DEFAULT nextval('department_id_seq') NOT NULL UNIQUE,
	code varchar(13) NOT NULL UNIQUE,
	name varchar(50) NOT NULL UNIQUE,
	number_of_employee int NOT NULL,
	is_activated int NOT NULL,
	updated_by varchar(50),
	created_timestamp timestamp with time zone,
	updated_timestamp timestamp with time zone,
	PRIMARY KEY (id)
) WITHOUT OIDS;


CREATE TABLE email_template
(
	id bigint DEFAULT nextval('email_template_id_seq') NOT NULL UNIQUE,
	title varchar(200) NOT NULL,
	body varchar(3000) NOT NULL,
	updated_by varchar(50),
	created_timestamp timestamp with time zone,
	updated_timestamp timestamp with time zone,
	PRIMARY KEY (id)
) WITHOUT OIDS;


CREATE TABLE pages
(
	id bigint DEFAULT nextval('page_id_seq') NOT NULL UNIQUE,
	name varchar(50) NOT NULL UNIQUE,
	code varchar(13) NOT NULL UNIQUE,
	url varchar(255) NOT NULL,
	updated_by varchar(50),
	created_timestamp timestamp with time zone,
	updated_timestamp timestamp with time zone,
	PRIMARY KEY (id)
) WITHOUT OIDS;


CREATE TABLE role
(
	id bigint DEFAULT nextval('role_id_seq') NOT NULL UNIQUE,
	code varchar(13) NOT NULL UNIQUE,
	name varchar(50) NOT NULL UNIQUE,
	is_activated int,
	updated_by varchar(50),
	created_timestamp timestamp with time zone,
	updated_timestamp timestamp with time zone,
	PRIMARY KEY (id)
) WITHOUT OIDS;


CREATE TABLE role_pages
(
	id bigint DEFAULT nextval('role_page_id_seq') NOT NULL UNIQUE,
	updated_by varchar(50),
	created_timestamp timestamp with time zone,
	updated_timestamp timestamp with time zone,
	role_id bigint NOT NULL,
	pages_id bigint NOT NULL,
	PRIMARY KEY (id)
) WITHOUT OIDS;


CREATE TABLE users
(
	id bigint DEFAULT nextval('user_id_seq') NOT NULL UNIQUE,
	first_name varchar(50),
	last_name varchar(50),
	dob date,
	email varchar(50) NOT NULL UNIQUE,
	username varchar(50) NOT NULL UNIQUE,
	password varchar(50) NOT NULL,
	registered_date date NOT NULL,
	activated_date date,
	seniority int,
	is_activated int,
	updated_by varchar(50),
	created_timestamp timestamp with time zone,
	updated_timestamp timestamp with time zone,
	PRIMARY KEY (id)
) WITHOUT OIDS;


CREATE TABLE user_department
(
	id bigint DEFAULT nextval('user_department_id_seq') NOT NULL UNIQUE,
	join_date date,
	leave_date date,
	stay_or_leave int,
	updated_by varchar(50),
	created_timestamp timestamp with time zone,
	updated_timestamp timestamp with time zone,
	department_id bigint NOT NULL,
	user_id bigint NOT NULL,
	role_id bigint DEFAULT nextval('role_id_seq') NOT NULL,
	PRIMARY KEY (id)
) WITHOUT OIDS;



/* Create Foreign Keys */

ALTER TABLE campaign_customer
	ADD FOREIGN KEY (campaign_id)
	REFERENCES campaign (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE campaign_customer
	ADD FOREIGN KEY (customer_id)
	REFERENCES customer (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE user_department
	ADD FOREIGN KEY (department_id)
	REFERENCES department (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE campaign
	ADD FOREIGN KEY (email_tempale_id)
	REFERENCES email_template (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE role_pages
	ADD FOREIGN KEY (pages_id)
	REFERENCES pages (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE role_pages
	ADD FOREIGN KEY (role_id)
	REFERENCES role (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE user_department
	ADD FOREIGN KEY (role_id)
	REFERENCES role (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE campaign_customer
	ADD FOREIGN KEY (user_id)
	REFERENCES users (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE user_department
	ADD FOREIGN KEY (user_id)
	REFERENCES users (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;



