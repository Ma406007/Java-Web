-- 1.根据性别分组,统计男性和女性员工的数量
select gender, count(*) from emp group by gender;

-- 2.查询入职时间在'2015-01-01'(包含)以前的员工,并对结果根据职位分组,获取员工数量大于等于2的职位
select job, count(*) from emp where entry_date <= '2015-01-01' group by job having count(*) >= 2;