-- 部门表
create table dept (
                      id int unsigned PRIMARY KEY AUTO_INCREMENT COMMENT 'ID, 主键',
                      name varchar(10) NOT NULL UNIQUE COMMENT '部门名称',
                      create_time datetime COMMENT '创建时间',
                      update_time datetime COMMENT '修改时间'
) COMMENT '部门表';

-- 员工表
create table emp(
                    id int unsigned primary key auto_increment comment 'ID,主键',
                    username varchar(20) not null unique comment '用户名',
                    password varchar(50) default '123456' comment '密码',
                    name varchar(10) not null comment '姓名',
                    gender tinyint unsigned not null comment '性别, 1:男, 2:女',
                    phone char(11) not null unique comment '手机号',
                    job tinyint unsigned comment '职位, 1 班主任, 2 讲师, 3 学工主管, 4 教研主管, 5 咨询师',
                    salary int unsigned comment '薪资',
                    image varchar(300) comment '头像',
                    entry_date date comment '入职日期',
                    dept_id int unsigned comment '部门ID',  -- 关联的是dept部门表的ID
                    create_time datetime comment '创建时间',
                    update_time datetime comment '修改时间'
) comment '员工表';

-- 员工工作经历表
create table emp_expr(
                         id int unsigned primary key auto_increment comment 'ID, 主键',
                         emp_id int unsigned null comment '员工ID', -- 关联的是emp员工表的ID
                         begin date null comment '开始时间',
                         end date null comment '结束时间',
                         company varchar(50) null comment '公司名称',
                         job varchar(50) null comment '职位'
) comment '工作经历';

-- 在上述的表结构设计中使用的都是逻辑外键