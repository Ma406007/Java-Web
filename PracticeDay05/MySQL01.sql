create table tb_user (
    id int primary key auto_increment comment 'ID,唯一标识',
    username varchar(20) not null unique comment '用户名',
    name varchar(10) not null comment '姓名',
    age int comment '年龄',
    gender char(1) default ('男') comment '性别'
) comment '用户表';