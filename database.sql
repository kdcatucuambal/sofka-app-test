

CREATE SCHEMA sofka_accounts;

CREATE TABLE sofka_accounts.tbl_accounts (
	acc_id bigserial NOT NULL,
	cli_id int8 NOT NULL,
	acc_state bool NOT NULL,
	acc_number varchar(255) NOT NULL,
	acc_balance numeric(38, 2) NOT NULL,
	acc_type varchar(255) NOT NULL,
	CONSTRAINT tbl_accounts_pkey null,
	CONSTRAINT uk_eqnckt7hybdukgvreymsm2vbf null
);


-- sofka_accounts.tbl_movements definition

-- Drop table

-- DROP TABLE sofka_accounts.tbl_movements;

CREATE TABLE sofka_accounts.tbl_movements (
	mov_id bigserial NOT NULL,
	mov_date timestamp(6) NOT NULL,
	mov_balance numeric(38, 2) NOT NULL,
	mov_type varchar(255) NOT NULL,
	mov_amount numeric(38, 2) NOT NULL,
	acc_id int8 NOT NULL,
	CONSTRAINT tbl_movements_pkey null
);


-- sofka_accounts.tbl_movements foreign keys

ALTER TABLE sofka_accounts.tbl_movements ADD CONSTRAINT fkn0510r5s5e2kk7u5fdrmwrj7r FOREIGN KEY (acc_id) REFERENCES sofka_accounts.tbl_accounts(acc_id);


CREATE SCHEMA sofka_customers;

-- sofka_customers.tbl_persons definition

-- Drop table

-- DROP TABLE sofka_customers.tbl_persons;

CREATE TABLE sofka_customers.tbl_persons (
	per_id bigserial NOT NULL,
	per_address varchar(255) NOT NULL,
	per_age int4 NOT NULL,
	per_genre varchar(255) NOT NULL,
	per_identification varchar(255) NOT NULL,
	per_name varchar(255) NOT NULL,
	per_phone varchar(255) NOT NULL,
	CONSTRAINT tbl_persons_pkey PRIMARY KEY (per_id),
	CONSTRAINT uk_ljgfo3ie52hduah36vbas52u8 UNIQUE (per_identification)
);


-- sofka_customers.tbl_clients definition

-- Drop table

-- DROP TABLE sofka_customers.tbl_clients;

CREATE TABLE sofka_customers.tbl_clients (
	cli_password varchar(255) NOT NULL,
	cli_estado bool NOT NULL,
	per_id int8 NOT NULL,
	CONSTRAINT tbl_clients_pkey PRIMARY KEY (per_id)
);


-- sofka_customers.tbl_clients foreign keys

ALTER TABLE sofka_customers.tbl_clients ADD CONSTRAINT fkhm3l61npv0555a8ptobmlywq3 FOREIGN KEY (per_id) REFERENCES sofka_customers.tbl_persons(per_id);