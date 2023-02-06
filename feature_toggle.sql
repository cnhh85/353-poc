CREATE TABLE public.feature_toggle (
	user_code varchar(255) NOT NULL,
	feature_name varchar(255) NOT NULL,
	activated bool NOT NULL,
	CONSTRAINT feature_toggle_pkey PRIMARY KEY (user_code, feature_name)
);
CREATE INDEX idx_feature_toggle_feature_name ON public.feature_toggle USING btree (feature_name);
CREATE INDEX idx_feature_toggle_user ON public.feature_toggle USING btree (user_code);