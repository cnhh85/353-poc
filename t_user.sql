-- public.t_user definition

-- Drop table

-- DROP TABLE public.t_user;

CREATE TABLE public.t_user (
	"password" varchar(100) NULL,
	first_name varchar(50) NULL,
	last_name varchar(50) NULL,
	email varchar(100) NULL,
	number_attempts_login int4 NULL,
	locked_until timestamptz NULL,
	sedex_status varchar(15) NULL,
	user_code varchar(20) NOT NULL,
	psw_modified_on timestamptz NULL,
	api_key varchar(100) NULL,
	organisation_code varchar(20) NULL,
	impersonated_user_code varchar(20) NULL,
	"language" varchar(100) NULL,
	created_on timestamptz NULL,
	modified_on timestamptz NULL,
	phone varchar(100) NULL,
	fax varchar(100) NULL,
	number_attempts_resend_registration_email int4 NULL,
	number_attempts_resend_registration_email_by_sedex_admin int4 NULL,
	email_notification_language varchar(255) NULL,
	last_logged_in_time timestamp NULL,
	previous_logged_in_time timestamp NULL,
	job_title varchar(100) NULL,
	department varchar(100) NULL,
	apsca_number varchar(100) NULL,
	apsca_status varchar(100) NULL,
	suspend_auditor bool NULL,
	gender varchar(50) NULL,
	elearning_code varchar(50) NULL,
	created_by_connect bool NULL,
	dummy_password varchar(100) NULL,
	CONSTRAINT pk_t_user PRIMARY KEY (user_code),
	CONSTRAINT user_email_constraint UNIQUE (email)
);
CREATE INDEX idx_org ON public.t_user USING btree (organisation_code);
CREATE INDEX idx_t_user_email_upper ON public.t_user USING gin (upper((email)::text) gin_trgm_ops);
CREATE INDEX idx_tuser_email ON public.t_user USING btree (email);

-- Table Triggers

create trigger bucardo_delta after
insert
    or
delete
    or
update
    on
    public.t_user for each row execute procedure bucardo.delta_public_t_user();
create trigger bucardo_kick_sync_rt_etl_sedex_global_prod after
insert
    or
delete
    or
update
    or
truncate
    on
    public.t_user for each statement execute procedure bucardo.bucardo_kick_sync_rt_etl_sedex_global_prod();
create trigger bucardo_note_trunc_sync_rt_etl_sedex_global_prod after
truncate
    on
    public.t_user for each statement execute procedure bucardo.bucardo_note_truncation('sync_rt_etl_sedex_global_prod');
create trigger evs_trigger_t_user after
insert
    or
delete
    or
update
    on
    public.t_user for each row execute procedure notify_event_sourcing();