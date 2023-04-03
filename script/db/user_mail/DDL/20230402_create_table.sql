
drop table IF EXISTS cs_user_mail;

CREATE TABLE cs_user_mail (
  user_mail_id    serial4       not null,
  mail 		      varchar(1023) not null,
  note        	  text          null,
  started_at      timestamptz   not null,
  ended_at	      timestamptz   null,	
  cs_user_id      int4          not null,
  enabled		  bool          not null,
  created_at      timestamptz   not null,
  updated_at	  timestamptz   not null,
  updated_by  	  varchar(15)   not null,
  deleted_at	  timestamptz   null,
  CONSTRAINT cs_user_mail_pk PRIMARY KEY (user_mail_id)
);

COMMENT ON COLUMN cs_user_mail.user_mail_id      IS 'Primary key of the table';
COMMENT ON COLUMN cs_user_mail.mail 			 IS 'Street name value';
COMMENT ON COLUMN cs_user_mail.started_at        IS 'Start datatime where live in this address';
COMMENT ON COLUMN cs_user_mail.ended_at          IS 'End datatime where live in this address';
COMMENT ON COLUMN cs_user_mail.cs_user_id        IS 'FK from cs_user table';
COMMENT ON COLUMN cs_user_mail.enabled 			 IS 'if it is 1 the record is active, otherwise it is disable';
COMMENT ON COLUMN cs_user_mail.created_at 		 IS 'Log time when created record on the table';
COMMENT ON COLUMN cs_user_mail.updated_at 		 IS 'Log time when updated record on the table';
COMMENT ON COLUMN cs_user_mail.updated_by 		 IS 'User that performed the operation';
COMMENT ON COLUMN cs_user_mail.deleted_at 		 IS 'Log time when logical deleted record on the table';

alter table cs_user_mail add CONSTRAINT cs_user_mail_user_id_FK FOREIGN KEY (user_id) REFERENCES cs_user(user_id);
