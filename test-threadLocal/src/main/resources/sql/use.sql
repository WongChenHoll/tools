create table account(
                        id varchar2(50),
                        name varchar2(50),
                        money NUMBER(5,2)
)tablespace TEST_DATA;
-- 初始化数据
insert into account values('111', 'Jack',100.00);
insert into account values('222', 'Rose',0);