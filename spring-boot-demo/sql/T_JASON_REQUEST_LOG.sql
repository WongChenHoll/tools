-- Create table
create table T_JASON_REQUEST_LOG
(
  id                VARCHAR2(50) not null,
  create_time       DATE not null,
  create_user       VARCHAR2(100) not null,
  request_time      DATE not null,
  request_interface VARCHAR2(100) not null,
  valid_flag        CHAR(1)
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
comment on table T_JASON_REQUEST_LOG
  is '用户请求日志表';
-- Add comments to the columns 
comment on column T_JASON_REQUEST_LOG.id
  is '主键';
comment on column T_JASON_REQUEST_LOG.create_time
  is '创建时间';
comment on column T_JASON_REQUEST_LOG.create_user
  is '创建人';
comment on column T_JASON_REQUEST_LOG.request_time
  is '请求时间';
comment on column T_JASON_REQUEST_LOG.request_interface
  is '请求接口';
comment on column T_JASON_REQUEST_LOG.valid_flag
  is '有效标识 1：有效，0无效';
