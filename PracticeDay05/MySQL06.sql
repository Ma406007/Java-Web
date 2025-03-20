-- 1.统计该企业员工数量
select count(id) from emp;

-- 2.统计该企业员工的平均薪资
select avg(salary) from emp;

-- 3.统计该企业员工的最低薪资
select min(salary) from emp;

-- 4.统计该企业员工的最高薪资
select max(salary) from emp;

-- 5.统计该企业每月要给员工发放的薪资总额(薪资之和)
select sum(salary) from emp;