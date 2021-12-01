-- Create table
create table T_JASON_USER
(
  id          VARCHAR2(50) not null,
  create_time DATE not null,
  create_user VARCHAR2(100) not null,
  user_name   VARCHAR2(100),
  age         NUMBER,
  address     VARCHAR2(2000)
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
-- Create/Recreate indexes 
create unique index PX_TJU on T_JASON_USER (ID)
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
