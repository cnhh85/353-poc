CREATE TABLE public.t_user_authority (
	user_code varchar(20) NULL,
	authority_id int8 NULL
);
CREATE UNIQUE INDEX idx_user_authority ON public.t_user_authority USING btree (user_code, authority_id);

-- Table Triggers

create trigger bucardo_delta after
insert
    or
delete
    or
update
    on
    public.t_user_authority for each row execute procedure bucardo.delta_public_t_user_authority();
create trigger bucardo_kick_sync_rt_etl_sedex_global_prod after
insert
    or
delete
    or
update
    or
truncate
    on
    public.t_user_authority for each statement execute procedure bucardo.bucardo_kick_sync_rt_etl_sedex_global_prod();
create trigger bucardo_note_trunc_sync_rt_etl_sedex_global_prod after
truncate
    on
    public.t_user_authority for each statement execute procedure bucardo.bucardo_note_truncation('sync_rt_etl_sedex_global_prod');
create trigger evs_trigger_t_user_authority after
insert
    or
delete
    or
update
    on
    public.t_user_authority for each row execute procedure notify_event_sourcing();


-- public.t_user_authority foreign keys

ALTER TABLE public.t_user_authority ADD CONSTRAINT fk_authority_name FOREIGN KEY (authority_id) REFERENCES public.t_authority(id);