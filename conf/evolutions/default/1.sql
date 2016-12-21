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




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table bar_chart_data;

drop table date_of_posts;

drop table silkroad_data;

SET FOREIGN_KEY_CHECKS=1;

