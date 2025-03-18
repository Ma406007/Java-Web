package org.example.Service.Impl;

import org.example.Dao.Impl.UserDaoImpl;
import org.example.Dao.UserDao;
import org.example.Service.UserService;
import org.example.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

//Service及Dao层的实现类交给IOC容器管理
//在实现类加上@Component注解就代表把当前类产生的对象交给IOC容器管理
//B.UserServiceImpl
@Service
@Component//控制反转
public class UserServiceImpl implements UserService {
    //1.调用Dao,获取数据
    //为Controller及Service注入运行时所依赖的对象
    //B.UserServiceImpl
    @Autowired//注入依赖
    private UserDao userDao;

    @Override
    public List<User> findAll() {
        List<String> lines = userDao.findAll();

        //2.解析数据，封装成对象 --> 集合
        List<User> userList = lines.stream().map(line -> {
            String[] parts = line.split(",");
            Integer id = Integer.parseInt(parts[0]);
            String username = parts[1];
            String password = parts[2];
            String name = parts[3];
            Integer age = Integer.parseInt(parts[4]);
            LocalDateTime updateTime = LocalDateTime.parse(parts[5], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            return new User(id, username, password, name, age, updateTime);
        }).toList();

        return userList;
    }
}
