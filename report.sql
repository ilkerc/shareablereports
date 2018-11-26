create table report
(
  id                 bigint not null
    constraint report_pkey
    primary key,
  report_title       varchar,
  report_description varchar,
  report_author      varchar
);

alter table report
  owner to postgres;
