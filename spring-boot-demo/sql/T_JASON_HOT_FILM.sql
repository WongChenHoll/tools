-- Create table
create table T_JASON_HOT_FILM
(
  id              VARCHAR2(32) not null,
  create_time     DATE not null,
  create_user     VARCHAR2(50) not null,
  biz_date        DATE not null,
  delete_flag     CHAR(1) not null,
  film_name       VARCHAR2(200) not null,
  director        VARCHAR2(200),
  screen_writer   VARCHAR2(200),
  major_star      VARCHAR2(1000),
  film_country    VARCHAR2(100),
  film_language   VARCHAR2(10),
  film_length     NUMBER(5),
  another_name    VARCHAR2(200),
  film_score      NUMBER(5,1),
  release_date    DATE,
  film_type       VARCHAR2(100),
  synopsis        CLOB,
  film_douban_url VARCHAR2(200),
  star_level      NUMBER(5,1),
  one_star        NUMBER(5,2),
  two_star        NUMBER(5,2),
  three_star      NUMBER(5,2),
  four_star       NUMBER(5,2),
  five_star       NUMBER(5,2)
)
tablespace TEST_DATA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table 
comment on table T_JASON_HOT_FILM
  is '热门电影表';
-- Add comments to the columns 
comment on column T_JASON_HOT_FILM.id
  is '主键';
comment on column T_JASON_HOT_FILM.create_time
  is '创建时间';
comment on column T_JASON_HOT_FILM.create_user
  is '创建人';
comment on column T_JASON_HOT_FILM.biz_date
  is '业务日期';
comment on column T_JASON_HOT_FILM.delete_flag
  is '删除标识 1：是，0：否';
comment on column T_JASON_HOT_FILM.film_name
  is '电影名称';
comment on column T_JASON_HOT_FILM.director
  is '导演';
comment on column T_JASON_HOT_FILM.screen_writer
  is '编剧';
comment on column T_JASON_HOT_FILM.major_star
  is '主演 多个主演用/分割';
comment on column T_JASON_HOT_FILM.film_country
  is '发行国家地区';
comment on column T_JASON_HOT_FILM.film_language
  is '语言';
comment on column T_JASON_HOT_FILM.film_length
  is '片长，电影时长 单位:分钟';
comment on column T_JASON_HOT_FILM.another_name
  is '别名';
comment on column T_JASON_HOT_FILM.film_score
  is '评分';
comment on column T_JASON_HOT_FILM.release_date
  is '发行日期';
comment on column T_JASON_HOT_FILM.film_type
  is '电影类型';
comment on column T_JASON_HOT_FILM.synopsis
  is '简介';
comment on column T_JASON_HOT_FILM.film_douban_url
  is '电影爬取网址 豆瓣网址';
comment on column T_JASON_HOT_FILM.star_level
  is '星级';
comment on column T_JASON_HOT_FILM.one_star
  is '一星占比 单位:%';
comment on column T_JASON_HOT_FILM.two_star
  is '二星占比 单位:%';
comment on column T_JASON_HOT_FILM.three_star
  is '三星占比 单位:%';
comment on column T_JASON_HOT_FILM.four_star
  is '四星占比 单位:%';
comment on column T_JASON_HOT_FILM.five_star
  is '五星占比 单位:%';
-- Create/Recreate indexes 
create index IDX_JASON_HOT_FILM_D on T_JASON_HOT_FILM (DIRECTOR)
  tablespace TEST_DATA
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index IDX_JASON_HOT_FILM_FN on T_JASON_HOT_FILM (FILM_NAME)
  tablespace TEST_DATA
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index IDX_JASON_HOT_FILM_RD on T_JASON_HOT_FILM (RELEASE_DATE)
  tablespace TEST_DATA
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_JASON_HOT_FILM
  add constraint PK_JASON_HOT_FILM primary key (ID)
  using index 
  tablespace TEST_DATA
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
