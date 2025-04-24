package org.example.controller;

import org.example.pojo.Dept;
import org.example.pojo.Result;
import org.example.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门管理控制器
 */
@RequestMapping("/depts")
@RestController
public class DeptController {
    //接收Service层的对象
    @Autowired
    private DeptService deptService;

    //返回值:最终给前端返回统一响应的数据
    //@RequestMapping(value = "/depts", method = RequestMethod.GET) //method:请求方式
    @GetMapping//限定请求方式为GET
    //相应的,@XxxMapping限定请求方式为Xxx
    public Result list() {
        System.out.println("查询全部部门的数据");
        List<Dept> deptList = deptService.findAll();
        //返回值是Result类的对象
        return Result.success(deptList);
    }

    /**
     * 根据ID删除部门 - 简单参数接收: 方式一 (HttpServletRequest)
     */
//    @DeleteMapping
//    public Result delete(HttpServletRequest request){
//        String idStr = request.getParameter("id");
//        int id = Integer.parseInt(idStr);
//
//        System.out.println("根据ID删除部门: " + id);
//        return Result.success();
//    }

    /*
      - 方案二:通过Spring提供的@RequestParam注解.将请求参数绑定给方法形参
      - 注意:1.@RequestParam注解的value属性需要与前端传递的参数名保持一致
            2.@RequestParam注解required属性默认为true,代表该参数必须传递,如果不传递将报错,如果参数可选可以将属性设置为false
    */
//    @DeleteMapping
//    public Result delete(@RequestParam(value = "id", required = false) Integer deptId){
//        System.out.println("根据ID删除部门: " + deptId);
//        return Result.success();
//    }


    // 方案三:如果前端传递的请求参数名与服务端方法的形参变量名相同,直接定义方法形参即可接收(省略@RequestParam)(推荐)
    @DeleteMapping
    public Result delete(Integer id) throws Exception {
        System.out.println("根据id删除部门" + id);
        deptService.deleteById(id);
        return Result.success();
    }

    @PostMapping
    public Result insert(@RequestBody Dept dept) {
        System.out.println("插入数据:" + dept.getName());
        deptService.insert(dept);
        return Result.success();
    }

    //根据id查询部门
    //路径参数:通过请求URL直接传递参数,使用{…}来标识该路径参数,需要使用@PathVariable获取路径参数
    //如果在url携带多个路径参数:"/depts/{id}/{sta}"(@pathVariable Integer id,@pathVariable Integer sta)
    //如果路径参数名与controller方法形参名称一致,@PathVariable注解的value属性是可以省略的
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        System.out.println("根据id查询部门:" + id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    @PutMapping
    public Result update(@RequestBody Dept dept) {
        System.out.println("修改数据:" + dept.getName());
        deptService.update(dept);
        return Result.success();
    }
}