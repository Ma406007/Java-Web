select * from dept, emp where dept.id = emp.dept_id;

-- 案例1:查询所有员工的ID,姓名及所属的部门名称
-- 隐式内连接:
select emp.id, emp.name, dept.name from dept, emp where dept.id = emp.dept_id;
-- 显式内连接:
select emp.id, emp.name, dept.name from dept join emp on dept.id = emp.dept_id;

-- 案例2:查询性别为男,且工资高于8000的员工的ID,姓名及所属的部门名称
-- 隐式内连接:
select emp.id, emp.name, dept.name from dept, emp where emp.gender = '1' and emp.salary > 8000 and dept.id = emp.dept_id;
-- 显式内连接:
select emp.id, emp.name, dept.name from dept join emp on dept.id = emp.dept_id where emp.gender = '1' and emp.salary > 8000;

-- 案例3:查询员工表所有员工的姓名和对应部门名称
-- 左外连接:
select e.name, d.name from emp e left join dept d on e.dept_id = d.id;
-- 右外连接:
select e.name, d.name from dept d right join emp e on e.dept_id = d.id;

-- 案例4:查询工资高于8000的所有员工的姓名和对应的部门名称
-- 左外连接:
select e.name, d.name from emp e left join dept d on e.dept_id = d.id where e.salary > 8000;
-- 右外连接:
select e.name, d.name from dept d right join emp e on e.dept_id = d.id where e.salary > 8000;

-- 案例5:查询最早入职的员工信息
select * from emp e where e.entry_date = (select min(entry_date) from emp);

-- 案例6:查询在阮小五入职之后入职的员工信息
select * from emp e where e.entry_date > (select e.entry_date from emp e where e.name = '阮小五');

-- 案例7:查询"教研部"和"咨询部"的所有员工信息
select * from emp where dept_id in (select id from dept where name = '教研部' || name = '咨询部');

-- 案例8:查询与"李忠"的薪资及职位都相同的员工信息
select salary, job from emp where name = '李忠';
select * from emp where (salary, job) = (select salary, job from emp where name = '李忠');

-- 案例9:获取每个部门中薪资最高的员工信息
-- a.获取每个部门的最高薪资
select dept_id, max(salary) from emp group by dept_id;
-- b.查询每个部门中薪资最高的员工信息
select * from emp e, (select dept_id, max(salary) max_sal from emp group by dept_id) a where e.dept_id = a.dept_id and e.salary = a.max_sal;

-- 案例10:查询"教研部"性别为男且在"2011-05-01"之后入职的员工信息
select * from emp where dept_id = (select id from dept where name = '教研部') and entry_date in (select entry_date from emp where entry_date > '2011-05-01') and gender = '1';

-- 案例11:查询工资低于公司平均工资的且性别为男的员工信息
select e.* from emp e, dept d where e.dept_id = d.id and e.salary < (select avg(salary) from emp) and e.gender = 1;

-- 案例12:查询部门的人数超过10人的部门名称
select d.name, count(*) from emp e, dept d where e.dept_id = d.id group by d.name having count(*) > 10;

-- 案例13:查询在"2010-05-01"后入职,且薪资高于10000的"教研部"员工信息,并根据薪资倒序排序
select * from emp where entry_date > '2010-05-01' and dept_id = (select id from dept where name = '教研部') and salary > 10000 order by salary desc;

-- 案例14:查询工资低于本部门平均工资的员工信息(难)
-- 查询每个部门的平均工资并建立一张临时表
select dept_id, avg(salary) avg_sal from emp group by dept_id;
select e.* from emp e, (select dept_id, avg(salary) avg_sal from emp group by dept_id) a where e.dept_id = a.dept_id and e.salary < a.avg_sal;