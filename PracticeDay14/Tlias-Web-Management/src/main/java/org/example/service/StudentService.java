package org.example.service;

import org.example.pojo.PageResult;
import org.example.pojo.Student;
import org.example.pojo.StudentQueryParam;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface StudentService {
    /**
     * 分页查询学生信息
     * */
    PageResult pagelist(StudentQueryParam studentQueryParam);

    /**
     * 新增学生信息
     * */
    void insert(Student student);

    /**
     * 根据id查询学生信息
     * */
    Student getById(Integer id);

    /**
     * 修改学生信息
     * */
    void update(Student student);

    /**
     * 根据id删除学生信息
     * */
    void deleteByIds(List<Integer> ids);

    /**
     * 违纪处理
     * */
    void violation(Integer id, Short score);
}
