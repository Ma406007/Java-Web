package org.example.service;

import org.example.pojo.Emp;
import org.example.pojo.EmpQueryParam;
import org.example.pojo.PageResult;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
    //查询所有员工及部门名称
    List<Emp> list();

    //查询总记录数
    Long count();

    /**
     * 分页查询
     * @param page 页码
     * @param pageSize 每页展示的条数
     * */
    //PageResult pagelist(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end);

    /**
     * 分页查询
     * @param empQueryParam 请求参数对象
     * */
    PageResult pagelist(EmpQueryParam empQueryParam);

    //新增用户数据
    void insert(Emp emp);

    //删除用户数据
    void deleteByIds(List<Integer> ids);

    //根据id查询用户信息
    Emp getById(Integer id);

    //修改员工
    void update(Emp emp);
}
