package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.pojo.*;
import org.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequestMapping("/students")
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * 分页查询学生信息
     * */
    @GetMapping
    public Result page(StudentQueryParam studentQueryParam) {
        log.info("查询学生信息, studentQueryParam:{}", studentQueryParam);
        PageResult pageResult = studentService.pagelist(studentQueryParam);
        return Result.success(pageResult);
    }

    /**
     * 新增学生信息
     * */
    @PostMapping
    public Result save(@RequestBody Student student) {
        log.info("新增学生信息, student={}", student);
        studentService.insert(student);
        return Result.success(student);
    }

    /**
     * 根据Id查询学生信息
     * */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("根据id查询学生信息:{}", id);
        Student student = studentService.getById(id);
        return Result.success(student);
    }

    /**
     * 修改学生信息
     * */
    @PutMapping
    public Result update(@RequestBody Student student) {
        log.info("修改学生信息:{}", student);
        studentService.update(student);
        return Result.success(student);
    }

    /**
     * 根据id删除学生信息
     * */
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable String ids) {
        log.info("批量删除学生信息: ids={}", ids);
        List<Integer> idList = Arrays.stream(ids.split(","))
                                    .map(Integer::parseInt)
                                    .collect(Collectors.toList());
        studentService.deleteByIds(idList);
        return Result.success();
    }

    /**
     * 违纪处理
     * */
    @PutMapping("/violation/{id}/{score}")
    public Result violation(@PathVariable Integer id, @PathVariable Short score) {
        log.info("根据学生id对学生进行违纪处理: id={}, score={}", id, score);
        studentService.violation(id, score);
        return Result.success();
    }
}
