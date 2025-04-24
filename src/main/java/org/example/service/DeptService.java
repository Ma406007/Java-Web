package org.example.service;

import org.example.pojo.Dept;

import java.util.List;

public interface DeptService {
    //查询所有部门
    List<Dept> findAll();

    //根据ID删除部门
    void deleteById(Integer id) throws Exception;

    //更新数据
    void insert(Dept dept);

    //根据ID查询部门
    Dept getById(Integer id);

    //根据ID修改数据
    void update(Dept dept);
}
