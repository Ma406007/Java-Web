package org.example.service;

import org.example.pojo.Clazz;
import org.example.pojo.ClazzQueryParam;
import org.example.pojo.EmpQueryParam;
import org.example.pojo.PageResult;

public interface ClazzService {
    /**
     * 班级列表查询
     * @param clazzQueryParam 请求参数对象
     * */
    PageResult pagelist(ClazzQueryParam clazzQueryParam);

    /**
     * 添加班级
     * */
    void insert(Clazz clazz) throws Exception;

    /**
     * 根据ID查询班级信息
     * */
    Clazz getById(Integer id);

    /**
     * 修改班级信息
     * */
    void update(Clazz clazz);

    /**
     * 删除班级信息
     * */
    void deleteById(Integer id);
}
