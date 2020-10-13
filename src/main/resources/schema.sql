CREATE TABLE person (
    person_id integer IDENTITY,
	client_id integer NOT NULL,
    first_name varchar(50) NOT NULL,
    last_name varchar(50) NOT NULL,
    email_address varchar(50) NOT NULL,
    street_address varchar(50) NOT NULL,
    city varchar(50) NOT NULL,
    state varchar(10) NOT NULL,
    zip_code varchar(10) NOT NULL
);
CREATE TABLE client (
    client_id integer IDENTITY,
    company_name varchar(100) NOT NULL,
    website_url varchar(100) NOT NULL,
    phone_number varchar(50) NOT NULL,
    street_address varchar(500) NOT NULL,
    city varchar(50) NOT NULL,
    state varchar(20) NOT NULL,
    zip_code varchar(10) NOT NULL
);
CREATE TABLE contact (
    contact_id integer IDENTITY,
	client_id integer NOT NULL,
    first_name varchar(50) NOT NULL,
    last_name varchar(50) NOT NULL,
    email_address varchar(50) NOT NULL,
    street_address varchar(50) NOT NULL,
    city varchar(50) NOT NULL,
    state varchar(2) NOT NULL,
    zip_code varchar(5) NOT NULL
);
