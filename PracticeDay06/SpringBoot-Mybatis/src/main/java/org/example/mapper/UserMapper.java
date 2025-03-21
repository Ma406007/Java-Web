package org.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.example.pojo.User;

//UserMapper是Mybatis中的一个持久层接口(Mybatis的持久层接口规范一般都叫XxxMapper)
@Mapper//程序在运行时会自动创建该接口的实现类对象(代理对象),并且会自动将该实现类对象存入IOC容器中--成为IOC容器中的一个bean
public interface UserMapper {

    //@Insert 新增
    //@Delete 删除
    //@Update 修改
    //@Select 查询

    //查询全部
    //@Select("select * from user")
    public List<User> selectAll();

    //根据ID删除用户信息
    @Delete("delete from user where id = #{id}")
    public void deleteById(Integer id);

    //添加一个用户
    @Insert("insert into user(username, password, name, age) values(#{username}, #{password}, #{name}, #{age})")
    public void insert(User user);

    //根据ID更新用户信息
    @Update("update user set username = #{username}, password = #{password}, name = #{name}, age = #{age} where id = #{id}")
    public void updateById(User user);

    //根据用户名和密码查询用户信息
    @Select("select * from user where username = #{username} and password = #{password}")
    public User select(@Param("用户名")String username, @Param("密码")String password);
}
