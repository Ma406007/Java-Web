package org.example.mapper;

import org.apache.ibatis.annotations.*;
import org.example.pojo.Student;
import org.example.pojo.StudentQueryParam;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {

    /**
     * 分页查询学生信息
     * */
    List<Student> pagelist(StudentQueryParam studentQueryParam);

    /**
     * 新增学生信息
     * */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into student(name, no, gender, phone, id_card, is_college, address, degree, graduation_date, clazz_id, create_time, update_time)" +
            "values(#{name}, #{no}, #{gender}, #{phone}, #{idCard}, #{isCollege}, #{address}, #{degree}, #{graduationDate}, #{clazzId}, #{createTime}, #{updateTime})")
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
    @Update("update student set violation_count = violation_count + 1, violation_score = violation_score + #{score}, update_time = now() where id = #{id}")
    void violation(Integer id, Short score);

    /**
     * 统计学员学历
     * */
    @MapKey("name")
    List<Map> countStudentDegreeData();

    /**
     * 统计各班级人数
     * */
    @Select("select c.name cname , count(s.id) scount from clazz c  left join student s on s.clazz_id = c.id group by c.name order by count(s.id) desc ")
    List<Map<String,Object>> getStudentCount();
}
