
/* Drop Tables */

DROP TABLE IF EXISTS department_detail;
DROP TABLE IF EXISTS department;
DROP TABLE IF EXISTS mail_histoy;
DROP TABLE IF EXISTS email;
DROP TABLE IF EXISTS role_detail;
DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS users;




/* Create Tables */

CREATE TABLE department
(
	department_id serial NOT NULL UNIQUE,
	department_name varchar(50),
	number_of_employees int,
	status int,
	PRIMARY KEY (department_id)
) WITHOUT OIDS;


CREATE TABLE department_detail
(
	department_detail_id serial NOT NULL UNIQUE,
	status int,
	department_id int NOT NULL,
	user_id int NOT NULL,
	PRIMARY KEY (department_detail_id)
) WITHOUT OIDS;


CREATE TABLE email
(
	email_id serial NOT NULL UNIQUE,
	subject varchar(200) NOT NULL,
	content varchar(3000) NOT NULL,
	PRIMARY KEY (email_id)
) WITHOUT OIDS;


CREATE TABLE mail_histoy
(
	mail_histoy_id serial NOT NULL UNIQUE,
	send_date date NOT NULL,
	user_id int NOT NULL,
	email_id int NOT NULL,
	PRIMARY KEY (mail_histoy_id)
) WITHOUT OIDS;


CREATE TABLE role
(
	role_id serial NOT NULL UNIQUE,
	role_name varchar(15) NOT NULL,
	PRIMARY KEY (role_id)
) WITHOUT OIDS;


CREATE TABLE role_detail
(
	role_detail_id serial NOT NULL UNIQUE,
	user_id int NOT NULL,
	role_id int NOT NULL,
	PRIMARY KEY (role_detail_id)
) WITHOUT OIDS;


CREATE TABLE users
(
	user_id serial NOT NULL UNIQUE,
	first_name varchar(50),
	last_name varchar(50),
	email varchar(50) NOT NULL,
	username varchar(50) NOT NULL,
	password varchar(50) NOT NULL,
	dob date,
	start_date date NOT NULL,
	end_date date NOT NULL,
	tenure int NOT NULL,
	status int,
	PRIMARY KEY (user_id)
) WITHOUT OIDS;



/* Create Foreign Keys */

ALTER TABLE department_detail
	ADD FOREIGN KEY (department_id)
	REFERENCES department (department_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE mail_histoy
	ADD FOREIGN KEY (email_id)
	REFERENCES email (email_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE role_detail
	ADD FOREIGN KEY (role_id)
	REFERENCES role (role_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE department_detail
	ADD FOREIGN KEY (user_id)
	REFERENCES users (user_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE mail_histoy
	ADD FOREIGN KEY (user_id)
	REFERENCES users (user_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE role_detail
	ADD FOREIGN KEY (user_id)
	REFERENCES users (user_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;



