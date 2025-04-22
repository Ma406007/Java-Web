package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.pojo.Emp;
import org.example.pojo.EmpQueryParam;
import org.example.pojo.PageResult;
import org.example.pojo.Result;
import org.example.service.EmpService;
import org.example.service.Impl.EmpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;
    @Autowired
    private EmpServiceImpl empServiceImpl;

//    @GetMapping
//    //@RequestParam(defaultValue = "默认值")设置请求参数默认值
//    public Result page(@RequestParam(defaultValue = "1") Integer page,
//                       @RequestParam(defaultValue = "10") Integer pageSize,
//                       String name, Integer gender,
//                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
//                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
//        log.info("查询员工信息, page={}, pageSize={}, name={}, gender={}, begin={}, end={}", page, pageSize, name, gender, begin, end);
//        PageResult pageResult = empService.pagelist(page, pageSize, name, gender, begin, end);
//        return Result.success(pageResult);
//    }

    /**
     * 分页查询优化方法一
     * 请求参数过多时可将请求参数封装在一个对象中然后调用
     * */
    @GetMapping
    //@RequestParam(defaultValue = "默认值")设置请求参数默认值
    public Result page(EmpQueryParam empQueryParam) {
        log.info("查询员工信息, empQueryParam={}", empQueryParam);
        PageResult pageResult = empService.pagelist(empQueryParam);
        return Result.success(pageResult);
    }

    /**
     * 新增员工
     * */
    @PostMapping
    public Result save(@RequestBody Emp emp) {
        log.info("新增用户数据, emp={}", emp);
        empService.insert(emp);
        return Result.success(emp);
    }

    //删除员工 方式一:数组
//    @DeleteMapping
//    public Result delete(Integer[] ids) {
//        log.info("批量删除部门: ids={}", Arrays.toString(ids));
//        return Result.success();
//    }

    //删除员工 方式二:list
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids) {
        log.info("批量删除部门: ids={}", ids);
        empService.deleteByIds(ids);
        return Result.success();
    }

    /**
     * 根据id查询员工信息
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("根据id查询用户信息:{}", id);
        Emp emp = empService.getById(id);
        return Result.success(emp);
    }

    //修改员工
    @PutMapping
    public Result update(@RequestBody Emp emp) {
        log.info("修改员工:{}", emp);
        empService.update(emp);
        return Result.success();
    }

    /**
     * 查询所有员工
     * */
    @GetMapping("/list")
    public Result list() {
        log.info("查询所有员工信息");
        List<Emp> list = empService.list();
        return Result.success(list);
    }
}