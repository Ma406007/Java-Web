package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.pojo.Clazz;
import org.example.pojo.ClazzQueryParam;
import org.example.pojo.PageResult;
import org.example.pojo.Result;
import org.example.service.ClazzService;
import org.example.service.EmpService;
import org.example.service.Impl.ClazzServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/clazzs")
@RestController
public class ClazzController {

    @Autowired
    private ClazzService clazzService;
    @Autowired
    private ClazzServiceImpl clazzServiceImpl;
    @Autowired
    private EmpService empService;

    /**
     * 班级列表查询
     * */
    @GetMapping
    public Result page(ClazzQueryParam clazzQueryParam) {
        log.info("查询班级信息, clazzQueryParam={}", clazzQueryParam);
        PageResult pageResult = clazzService.pagelist(clazzQueryParam);
        return Result.success(pageResult);
    }

    /**
     * 添加班级
     * */
    @PostMapping
    public Result add(@RequestBody Clazz clazz) throws Exception {
        log.info("新增班级, clazz={}", clazz);
        clazzService.insert(clazz);
        return Result.success(clazz);
    }

    /**
     * 根据ID查询班级
     * */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("根据id查询班级信息:{}", id);
        Clazz clazz = clazzService.getById(id);
        return Result.success(clazz);
    }

    /**
     * 修改班级信息
     * */
    @PutMapping
    public Result update(@RequestBody Clazz clazz) {
        log.info("修改班级{}", clazz);
        clazzService.update(clazz);
        return Result.success(clazz);
    }

    /**
     * 删除班级信息
     * */
    @DeleteMapping("/{id}")
    public Result delete(Integer id) {
        log.info("根据id删除班级{}", id);
        clazzService.deleteById(id);
        return Result.success();
    }
}
