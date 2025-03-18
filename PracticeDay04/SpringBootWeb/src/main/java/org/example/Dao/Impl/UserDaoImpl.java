package org.example.Dao.Impl;

import cn.hutool.core.io.IoUtil;
import org.example.Dao.UserDao;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

//Service及Dao层的实现类交给IOC容器管理
//在实现类加上@Component注解就代表把当前类产生的对象交给IOC容器管理
//A.UserDaoImpl
@Repository
@Component//控制反转
public class UserDaoImpl implements UserDao {
    @Override
    public List<String> findAll() {
        //1.加载并读取文件
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("user.txt");
        ArrayList<String> lines = IoUtil.readLines(in, StandardCharsets.UTF_8, new ArrayList<>());
        return lines;
    }
}
