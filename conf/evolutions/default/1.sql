# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table bar_chart_data (
  username                  varchar(255),
  numberofposts             integer,
  topfiveposts              varchar(255),
  mostactivedate            varchar(255),
  recentpost                TEXT)
;

create table date_of_posts (
  numberofposts             integer,
  dateofposts               varchar(255))
;

create table silkroad_data (
  source                    varchar(255),
  topic                     TEXT,
  discussionnumber          varchar(255),
  username                  varchar(255),
  publishdate               varchar(255),
  content                   TEXT,
  language                  varchar(255))
;

create table user (
  id                        integer auto_increment not null,
  firstname                 varchar(255),
  lastname                  varchar(255),
  adress                    TEXT,
  city                      varchar(255),
  state                     varchar(255),
  zip                       varchar(255),
  title                     varchar(255),
  company                   varchar(255),
  phonenumber               varchar(255),
  email                     varchar(255),
  website                   varchar(255),
  password                  varchar(255),
  constraint uq_user_email unique (email),
  constraint pk_user primary key (id))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table bar_chart_data;

drop table date_of_posts;

drop table silkroad_data;

drop table user;

SET FOREIGN_KEY_CHECKS=1;

