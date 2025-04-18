package org.example.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.mapper.EmpExprMapper;
import org.example.mapper.EmpMapper;
import org.example.pojo.*;
import org.example.service.EmpLogService;
import org.example.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Arrays;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpExprMapper empExprMapper;

    @Autowired
    private EmpLogService empLogService;

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

    /**
     * 注解：@Transactional
     * 作用：在当前这个方法执行开始之前来开启事务，方法执行完毕之后提交事务。如果在这个方法执行的过程当中出现了异常，就会进行事务的回滚操作。
     * 位置：业务层的方法上、类上、接口上
     *   - 方法上(推荐)：当前方法交给spring进行事务管理
     *   - 类上：当前类中所有的方法都交由spring进行事务管理
     *   - 接口上：接口下所有的实现类当中所有的方法都交给spring 进行事务管理
     * */

    /**
     * 注解@Transactional当中的两个常见的属性：
     * - 异常回滚的属性：rollbackFor -- rollbackFor + 异常类型.class 用来指定只有出现特定种类的异常时才会进行事务回滚
     * - 事务传播行为：propagation
     *  */
    @Transactional(rollbackFor = Exception.class)//默认情况下只有出现RuntimeException(运行时异常)才会回滚事务
    @Override
    public void insert(Emp emp) {
        try {
            //1.补全基本属性
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());

            //2.保存员工基本信息
            empMapper.insert(emp);

            //int i = 1 / 0;

            //3.保存员工的工作经历信息(批量)
            Integer empId = emp.getId();
            List<EmpExpr> exprList = emp.getExprList();
            System.out.println(exprList);
            //批量插入员工工作经历信息
            if (!CollectionUtils.isEmpty(exprList)) {
                exprList.forEach(expr -> expr.setEmpId(empId));
                empExprMapper.insertBatch(exprList);
            }
        } finally {
            //记录操作日志
            EmpLog empLog = new EmpLog(null, LocalDateTime.now(), emp.toString());
            empLogService.insertLog(empLog);
        }

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteByIds(List<Integer> ids) {
        //1.批量删除员工基本信息
        empMapper.deleteByIds(ids);

        //2.批量删除员工的工作经历信息
        empExprMapper.deleteByEmpIds(ids);
    }

    @Override
    public Emp getById(Integer id) {
        return empMapper.getById(id);
    }

    @Override
    public void update(Emp emp) {
        //1.根据员工ID修改员工的信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);

        //2.根据员工ID修改员工的工作经历信息(先删后增)
        //2.1先根据员工ID删除原有的工作经历
        empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));

        //2.2再添加这个员工新的工作经历
        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)) {
            exprList.forEach(expr -> expr.setEmpId(emp.getId()));
            empExprMapper.insertBatch(exprList);
        }
    }
}
