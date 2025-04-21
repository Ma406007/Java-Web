package org.example.mapper;

import org.apache.ibatis.annotations.*;
import org.example.pojo.Emp;
import org.example.pojo.EmpQueryParam;

import java.util.List;
import java.util.Map;

//员工信息
@Mapper
public interface EmpMapper {
    /* 原始的分页查询的方式 */

    //查询所有的员工及其对应的部门名称
    @Select("select e.*, d.name as deptName from emp e left join dept d on e.dept_id = d.id")
    List<Emp> list();

    //查询总记录数
    @Select("select count(*) from emp e left join dept d on e.dept_id = d.id")
    Long count();

    //分页查询(原始方法)
//    @Select("select e.*, d.name deptName from emp e left join dept d on e.dept_id = d.id " +
//            "order by e.update_time desc limit #{start}, #{pageSize}")
//    List<Emp> pagelist(Integer start, Integer pageSize);

    //分页查询(PageHelper方法)
    //List<Emp> pagelist(String name, Integer gender, LocalDate begin, LocalDate end);

    //分页查询优化方法一
    List<Emp> pagelist(EmpQueryParam empQueryParam);


    /**
     * 新增员工数据
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")//获取到生成的主键 -- 主键返回
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time) " +
            "values (#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);

    /**
     * 批量删除员工信息
     */
    void deleteByIds(List<Integer> ids);

    /**
     * 根据id查询用户信息
     * */
    Emp getById(Integer id);

    /**
     * 修改员工
     * */
    void update(Emp emp);

    /**
     * 统计各个职位的员工人数
     */
    @MapKey("pos")
    List<Map<String,Object>> countEmpJobData();

    /**
     * 统计员工性别信息
     */
    @MapKey("name")
    List<Map> countEmpGenderData();
}