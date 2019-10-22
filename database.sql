
/* Drop Tables */

DROP TABLE IF EXISTS campaign_customer;
DROP TABLE IF EXISTS campaign;
DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS user_department;
DROP TABLE IF EXISTS department;
DROP TABLE IF EXISTS email_template;
DROP TABLE IF EXISTS role_pages;
DROP TABLE IF EXISTS pages;
DROP TABLE IF EXISTS user_role;
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
	campaign_id bigint DEFAULT nextval('campaign_id_seq') NOT NULL UNIQUE,
	title varchar(50) NOT NULL,
	description varchar(1000),
	duration int,
	start_date date,
	end_date date,
	updated_by varchar(50),
	created_timestamp timestamp with time zone,
	updated_timestamp timestamp with time zone,
	email_template_id bigint DEFAULT nextval('email_template_id_seq') NOT NULL,
	PRIMARY KEY (campaign_id)
) WITHOUT OIDS;


CREATE TABLE campaign_customer
(
	campaign_customer_id bigint DEFAULT nextval('campaign_customer_id_seq') NOT NULL UNIQUE,
	join_date date NOT NULL,
	sent_date date NOT NULL,
	updated_by varchar(50),
	created_timestamp timestamp with time zone,
	updated_timestamp timestamp with time zone,
	campaign_id bigint NOT NULL,
	user_id bigint DEFAULT nextval('user_id_seq') NOT NULL,
	customer_id bigint NOT NULL,
	PRIMARY KEY (campaign_customer_id)
) WITHOUT OIDS;


CREATE TABLE customer
(
	customer_id bigint DEFAULT nextval('customer_id_seq') NOT NULL UNIQUE,
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
	PRIMARY KEY (customer_id)
) WITHOUT OIDS;


CREATE TABLE department
(
	department_id bigint DEFAULT nextval('department_id_seq') NOT NULL UNIQUE,
	department_code varchar(13) NOT NULL UNIQUE,
	department_name varchar(50) NOT NULL UNIQUE,
	number_of_employee int NOT NULL,
	is_activated int NOT NULL,
	updated_by varchar(50),
	created_timestamp timestamp with time zone,
	updated_timestamp timestamp with time zone,
	PRIMARY KEY (department_id)
) WITHOUT OIDS;


CREATE TABLE email_template
(
	email_template_id bigint DEFAULT nextval('email_template_id_seq') NOT NULL UNIQUE,
	title varchar(200) NOT NULL,
	body varchar(3000) NOT NULL,
	updated_by varchar(50),
	created_timestamp timestamp with time zone,
	updated_timestamp timestamp with time zone,
	PRIMARY KEY (email_template_id)
) WITHOUT OIDS;


CREATE TABLE pages
(
	page_id bigint DEFAULT nextval('page_id_seq') NOT NULL UNIQUE,
	page_name varchar(50) NOT NULL,
	page_code varchar(13) NOT NULL,
	url varchar(255) NOT NULL,
	updated_by varchar(50),
	created_timestamp timestamp with time zone,
	updated_timestamp timestamp with time zone,
	PRIMARY KEY (page_id)
) WITHOUT OIDS;


CREATE TABLE role
(
	role_id bigint DEFAULT nextval('role_id_seq') NOT NULL UNIQUE,
	role_code varchar(13) NOT NULL,
	role_name varchar(15) NOT NULL,
	updated_by varchar(50),
	created_timestamp timestamp with time zone,
	updated_timestamp timestamp with time zone,
	PRIMARY KEY (role_id)
) WITHOUT OIDS;


CREATE TABLE role_pages
(
	role_page_id bigint DEFAULT nextval('role_page_id_seq') NOT NULL UNIQUE,
	updated_by varchar(50),
	created_timestamp timestamp with time zone,
	updated_timestamp timestamp with time zone,
	page_id bigint NOT NULL,
	role_id bigint DEFAULT nextval('role_id_seq') NOT NULL,
	PRIMARY KEY (role_page_id)
) WITHOUT OIDS;


CREATE TABLE users
(
	user_id bigint DEFAULT nextval('user_id_seq') NOT NULL UNIQUE,
	first_name varchar(50),
	last_name varchar(50),
	dob date,
	email varchar(50) NOT NULL,
	username varchar(50) NOT NULL,
	password varchar(50) NOT NULL,
	registered_date date NOT NULL,
	activated_date date,
	seniority int,
	is_activated int,
	updated_by varchar(50),
	created_timestamp timestamp with time zone,
	updated_timestamp timestamp with time zone,
	PRIMARY KEY (user_id)
) WITHOUT OIDS;


CREATE TABLE user_department
(
	user_department_id bigint DEFAULT nextval('user_department_id_seq') NOT NULL UNIQUE,
	join_date date,
	leave_date date,
	stay_or_leave int,
	updated_by varchar(50),
	created_timestamp timestamp with time zone,
	updated_timestamp timestamp with time zone,
	department_id bigint NOT NULL,
	user_id bigint NOT NULL,
	PRIMARY KEY (user_department_id)
) WITHOUT OIDS;


CREATE TABLE user_role
(
	user_role_id bigint DEFAULT nextval('user_role_id_seq') NOT NULL UNIQUE,
	join_date date,
	leave_date date,
	updated_by varchar(50),
	created_timestamp timestamp with time zone,
	updated_timestamp timestamp with time zone,
	user_id bigint NOT NULL,
	role_id bigint NOT NULL,
	PRIMARY KEY (user_role_id)
) WITHOUT OIDS;



/* Create Foreign Keys */

ALTER TABLE campaign_customer
	ADD FOREIGN KEY (campaign_id)
	REFERENCES campaign (campaign_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE campaign_customer
	ADD FOREIGN KEY (customer_id)
	REFERENCES customer (customer_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE user_department
	ADD FOREIGN KEY (department_id)
	REFERENCES department (department_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE campaign
	ADD FOREIGN KEY (email_template_id)
	REFERENCES email_template (email_template_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE role_pages
	ADD FOREIGN KEY (page_id)
	REFERENCES pages (page_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE role_pages
	ADD FOREIGN KEY (role_id)
	REFERENCES role (role_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE user_role
	ADD FOREIGN KEY (role_id)
	REFERENCES role (role_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE campaign_customer
	ADD FOREIGN KEY (user_id)
	REFERENCES users (user_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE user_department
	ADD FOREIGN KEY (user_id)
	REFERENCES users (user_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE user_role
	ADD FOREIGN KEY (user_id)
	REFERENCES users (user_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;



