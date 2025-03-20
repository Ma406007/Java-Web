-- 1.根据入职时间,对员工进行升序排序
select * from emp order by entry_date;

-- 2.根据入职时间,对员工进行降序排序
select * from emp order by entry_date DESC;

-- 3.根据入职时间对公司的员工进行升序排序,入职时间相同再按照更新时间进行降序排序
select * from emp order by entry_date, update_time DESC;