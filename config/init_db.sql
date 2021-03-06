create table resume
(
    uuid      varchar not null
        constraint resume_pk
            primary key,
    full_name text     not null
);

create table contact
(
    id          serial   not null,
    resume_uuid char(36) not null
        constraint contact_resume_uuid_fk
            references resume
            on update restrict on delete cascade,
    type        text     not null,
    value       text

);


