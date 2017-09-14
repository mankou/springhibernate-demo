drop table t_user;
create table t_user(
       id number,
       code varchar2(32),
       name varchar2(32),
       primary key(id)
);

insert into t_user(id,code,name) values(-1,'1code','1name');
insert into t_user(id,code,name) values(-2,'2code','2name');

commit;

