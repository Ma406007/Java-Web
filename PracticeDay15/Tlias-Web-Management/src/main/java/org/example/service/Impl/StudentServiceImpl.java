package org.example.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.mapper.StudentMapper;
import org.example.pojo.PageResult;
import org.example.pojo.Student;
import org.example.pojo.StudentQueryParam;
import org.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    /**
     * 分页查询学生信息
     */
    @Override
    public PageResult pagelist(StudentQueryParam studentQueryParam) {
        //1.设置分页参数
        PageHelper.startPage(studentQueryParam.getPage(), studentQueryParam.getPageSize());

        //2.执行查询
        List<Student> studentList = studentMapper.pagelist(studentQueryParam);
        Page<Student> p = (Page<Student>)studentList;

        //3.封装结果
        return new PageResult(p.getTotal(), studentList);
    }

    /**
     * 新增学生信息
     * */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insert(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());

        studentMapper.insert(student);
    }

    /**
     * 根据id查询学生信息
     * */
    @Override
    public Student getById(Integer id) {
        return studentMapper.getById(id);
    }

    /**
     * 修改学生信息
     * */
    @Override
    public void update(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.update(student);
    }

    /**
     * 根据id删除学生信息
     * */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteByIds(List<Integer> ids) {
        studentMapper.deleteByIds(ids);
    }

    /**
     * 违纪处理
     * */
    @Override
    public void violation(Integer id, Short score) {
        studentMapper.violation(id, score);
    }
}
