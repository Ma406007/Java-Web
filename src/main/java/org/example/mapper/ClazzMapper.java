package org.example.mapper;

import org.apache.ibatis.annotations.*;
import org.example.pojo.Clazz;
import org.example.pojo.ClazzQueryParam;

import java.util.List;

@Mapper
public interface ClazzMapper {

    /**
     * 班级列表查询
     * */
    List<Clazz> pagelist(ClazzQueryParam clazzQueryParam);

    @Options(useGeneratedKeys = true, keyProperty = "id")//获取到生成的主键 -- 主键返回
    @Insert("insert into clazz(id, name, room, begin_date, end_date, master_id, subject, create_time, update_time)" +
            "values(#{id}, #{name}, #{room}, #{beginDate}, #{endDate}, #{masterId}, #{subject}, #{createTime}, #{updateTime})")
    void insert(Clazz clazz);

    Clazz getById(Integer id);

    /**
     * 修改班级信息
     * */
    void update(Clazz clazz);

    /**
     * 删除班级信息
     * */
    @Delete("delete from clazz where id = #{id}")
    void deleteById(Integer id);

    /**
     * 查询所有班级信息
     * */
    @Select("select c.* from clazz c left join emp e on e.id = c.master_id")
    List<Clazz> list();
}
