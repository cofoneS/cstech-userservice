DROP SCHEMA IF EXISTS userservice;

CREATE SCHEMA IF NOT EXISTS csuserapp;

--############################################################
-- DDL tabella user
--############################################################

drop table IF EXISTS csuserapp.cs_user;

CREATE TABLE csuserapp.cs_user (
	user_id 		serial4      not null,
	firstname 		varchar(255) not null,
	surname			varchar(255) not null,
	user_key		varchar(255) not null,
	tin             varchar(255) not null,
	tin_country_key	varchar(31)  not null,
	nickname		varchar(255) null,
	avatar			varchar(511) null,
	enabled			bool         not null,
	created_at      timestamptz  not null,
	updated_at	    timestamptz  not null,
	updated_by  	varchar(15)  not null,
	deleted_at	    timestamptz  null,
	CONSTRAINT cs_user_pk PRIMARY KEY (user_id)
);

COMMENT ON COLUMN csuserapp.cs_user.user_id         IS 'Primary key of the table';
COMMENT ON COLUMN csuserapp.cs_user.firstname       IS 'Name of the user';
COMMENT ON COLUMN csuserapp.cs_user.surname         IS 'Surname of the user';
COMMENT ON COLUMN csuserapp.cs_user.user_key        IS 'Unique key to indefy the user';
COMMENT ON COLUMN csuserapp.cs_user.tin             IS 'Unique value with tin_country to indentify user';
COMMENT ON COLUMN csuserapp.cs_user.tin_country_key IS 'Unique value with tin to indentify user';
COMMENT ON COLUMN csuserapp.cs_user.nickname        IS 'Nickname value';
COMMENT ON COLUMN csuserapp.cs_user.avatar          IS 'Avatar image path';
COMMENT ON COLUMN csuserapp.cs_user.enabled         IS 'if it is 1 the record is active, otherwise it is disable';
COMMENT ON COLUMN csuserapp.cs_user.created_at      IS 'Log time when created record on the table';
COMMENT ON COLUMN csuserapp.cs_user.updated_at      IS 'Log time when updated record on the table';
COMMENT ON COLUMN csuserapp.cs_user.updated_by      IS 'User that performed the operation';
COMMENT ON COLUMN csuserapp.cs_user.deleted_at      IS 'Log time when logical deleted record on the table';

CREATE UNIQUE INDEX cs_user_user_key_UK1 ON csuserapp.cs_user (user_key);
CREATE UNIQUE INDEX cs_user_tin_UK2 ON csuserapp.cs_user (tin, tin_country_key);