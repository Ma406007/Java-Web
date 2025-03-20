-- 1.查询姓名为'宋江'的员工
select * from emp where name = '宋江';

-- 2.查询薪资小于等于5000的员工信息
select * from emp where salary <= 5000;

-- 3.查询没有分配职位的员工信息
select * from emp where job is null;

-- 4.查询有职位的员工信息
select * from emp where job is not null;

-- 5.查询密码不等于'123456'的员工信息
select * from emp where password != '123456';

-- 6.查询入职日期在'2000-01-01'(包含)到'2010-01-01'(包含)之间的员工信息
select * from emp where entry_date between '2000-01-01' and '2010-01-01';

-- 7.查询入职时间在'2000-01-01'(包含)到'2010-01-01'(包含)之间且性别为女的员工信息
select * from emp where entry_date between '2000-01-01' and '2010-01-01' and gender = '2';

-- 8.查询职位是2(讲师),3(学工主管),4(教研主管)的员工信息
select * from emp where job = 2 or job = 3 or job = 4;

-- 9.查询姓名是两个字的员工信息
select * from emp where name like '__';

-- 10.查询姓'张'的员工信息
select * from emp where name like '张%';

-- 11.查询姓名中包含'二'的员工信息
select * from emp where name like '%二%'