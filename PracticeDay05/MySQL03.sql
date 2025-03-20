-- 1.批量向emp表的username,name,gender字段插入数据
insert into emp(username, name, gender, phone_number, create_time, update_time) values('Tom', '汤姆', '男', '13876543920', now(), now());

-- 2.向emp表的所有字段插入数据
insert into emp values(2, 'wuji', '123456789', '张无忌', '男', '18953200406', 2, '15000', '23333', now(), now(), now());

-- 3.批量向emp表的username,name,gender字段插入数据
insert into emp(username, name, gender) values('Amy', '艾米', '女'), ('Ella', '艾拉', '女'), ('David', '戴维', '男');

-- 4.将emp表中id为1的员工的姓名name字段更新为'张三'
update emp set name = '张三' where id = 1;

-- 5.将emp表中的所有员工入职日期更新为'2010-01-01'
update emp set entry_date = '2010-01-01';

-- 6.删除emp表中id为1的员工
delete from emp where id = 1;

-- 7.删除emp表中所有员工
delete from emp;