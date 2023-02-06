CREATE TABLE public.user_preferences (
	preference_id serial4 NOT NULL,
	user_code varchar(20) NOT NULL,
	notification_type_id int4 NOT NULL,
	preference_field_id int4 NOT NULL,
	user_choice bool NOT NULL,
	last_modified_on timestamp NULL,
	last_modified_by varchar(100) NULL,
	CONSTRAINT const_user_preferences UNIQUE (user_code, notification_type_id, preference_field_id),
	CONSTRAINT pk_user_preferences PRIMARY KEY (preference_id)
);


-- public.user_preferences foreign keys

ALTER TABLE public.user_preferences ADD CONSTRAINT fk_notification FOREIGN KEY (notification_type_id) REFERENCES public.notification_type(notification_type_id);
ALTER TABLE public.user_preferences ADD CONSTRAINT fk_preference_field_id FOREIGN KEY (preference_field_id) REFERENCES public.user_preferences_fields(preference_field_id);
ALTER TABLE public.user_preferences ADD CONSTRAINT fk_user_code_preferences FOREIGN KEY (user_code) REFERENCES public.t_user(user_code);