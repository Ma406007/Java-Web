package org.example.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.mapper.EmpExprMapper;
import org.example.mapper.EmpMapper;
import org.example.pojo.Emp;
import org.example.pojo.EmpExpr;
import org.example.pojo.EmpQueryParam;
import org.example.pojo.PageResult;
import org.example.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpExprMapper empExprMapper;

    @Override
    public List<Emp> list() {
        return empMapper.list();
    }

    @Override
    public Long count() {
        return empMapper.count();
    }

    /**
     * 原始分页查询
     * @param page 页码
     * @param pageSize 每页展示的条数
     * */
//    @Override
//    public PageResult pagelist(Integer page, Integer pageSize) {
//        //1.调用Mapper接口查询总记录数
//        Long total = empMapper.count();
//
//        //2.调用Mapper接口查询结果列表
//        Integer start = (page - 1) * pageSize;
//        List<Emp> rows = empMapper.pagelist(start, pageSize);
//
//        //3.封装PageResult类
//        return new PageResult(total, rows);
//    }

    /**
     * PageHelper分页查询
     * @param page 页码
     * @param pageSize 每页展示的条数
     * @param name 查询用户姓名
     * @param gender 查询用户性别
     * @param begin 查询用户入职时间左区间
     *
     * */
    /*
      注意:
        1.PageHelper实现分页查询时,SQL语句的结尾一定一定一定不要加分号(;)
        2.PageHelper只会对紧跟在其后的第一条SQL语句进行分页处理
    */
//    @Override
//    public PageResult pagelist(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end) {
//        //1.设置分页参数
//        PageHelper.startPage(page,pageSize);
//
//        //2.执行查询
//        List<Emp> empList = empMapper.pagelist(name, gender, begin, end);
//        Page<Emp> p = (Page<Emp>)empList;
//
//        //3.封装结果
//        return new PageResult(p.getTotal(), p.getResult());
//    }

    /**
     * PageHelper分页查询
     * @param empQueryParam 请求参数对象
     * */
    /*
      注意:
        1.PageHelper实现分页查询时,SQL语句的结尾一定一定一定不要加分号(;)
        2.PageHelper只会对紧跟在其后的第一条SQL语句进行分页处理
    */
    @Override
    public PageResult pagelist(EmpQueryParam empQueryParam) {
        //1.设置分页参数
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());

        //2.执行查询
        List<Emp> empList = empMapper.pagelist(empQueryParam);
        Page<Emp> p = (Page<Emp>)empList;

        //3.封装结果
        return new PageResult(p.getTotal(), p.getResult());
    }

    @Override
    public void insert(Emp emp) {
        //1.补全基本属性
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());

        //2.保存员工基本信息
        empMapper.insert(emp);

        //3.保存员工的工作经历信息(批量)
        Integer empId = emp.getId();
        List<EmpExpr> exprList = emp.getExprList();
        System.out.println(exprList);
        //批量插入员工工作经历信息
        if (!CollectionUtils.isEmpty(exprList)) {
            exprList.forEach(expr -> expr.setEmpId(empId));
            empExprMapper.insertBatch(exprList);
        }
    }
}
