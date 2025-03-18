package org.example.Dao;

import java.util.List;

public interface UserDao {
    //加载用户数据
    public List<String> findAll();
}
