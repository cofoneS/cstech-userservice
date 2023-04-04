
drop table IF EXISTS csuserapp.cs_user_address;

CREATE TABLE csuserapp.cs_user_address (
	user_address_id 	serial4       not null,
	street 		        varchar(1023) not null,
	postal_code	        varchar(63)   not null,
	street_number		varchar(31)   not null,
	city_key            varchar(31)   not null,
	note        		text          null,
	started_at          timestamptz   not null,
	ended_at	        timestamptz   null,	
	user_id             int4          not null,
	enabled				bool          not null,
	created_at          timestamptz   not null,
	updated_at	        timestamptz   not null,
	updated_by  		varchar(15)   not null,
	deleted_at	        timestamptz   null,
	CONSTRAINT cs_user_address_pk PRIMARY KEY (user_address_id)
);

COMMENT ON COLUMN csuserapp.cs_user_address.user_address_id    IS 'Primary key of the table';
COMMENT ON COLUMN csuserapp.cs_user_address.street 			   IS 'Street name value';
COMMENT ON COLUMN csuserapp.cs_user_address.postal_code        IS 'Postal code value';
COMMENT ON COLUMN csuserapp.cs_user_address.street_number      IS 'Number of the street';
COMMENT ON COLUMN csuserapp.cs_user_address.city_key           IS 'The street key of the street and it is fk from address service';
COMMENT ON COLUMN csuserapp.cs_user_address.started_at         IS 'Start datatime where live in this address';
COMMENT ON COLUMN csuserapp.cs_user_address.ended_at           IS 'End datatime where live in this address';
COMMENT ON COLUMN csuserapp.cs_user_address.user_id            IS 'FK from cs_user table';
COMMENT ON COLUMN csuserapp.cs_user_address.enabled 		   IS 'if it is 1 the record is active, otherwise it is disable';
COMMENT ON COLUMN csuserapp.cs_user_address.created_at 		   IS 'Log time when created record on the table';
COMMENT ON COLUMN csuserapp.cs_user_address.updated_at         IS 'Log time when updated record on the table';
COMMENT ON COLUMN csuserapp.cs_user_address.updated_by         IS 'User that performed the operation';
COMMENT ON COLUMN csuserapp.cs_user_address.deleted_at         IS 'Log time when logical deleted record on the table';

alter table csuserapp.cs_user_address add CONSTRAINT cs_user_address_user_id_FK FOREIGN KEY (user_id) REFERENCES csuserapp.cs_user (user_id);
