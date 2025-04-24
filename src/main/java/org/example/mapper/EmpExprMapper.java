package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.pojo.Emp;
import org.example.pojo.EmpExpr;

import java.util.List;

//员工工作经历
@Mapper
public interface EmpExprMapper {
    //批量插入员工工作经历信息
    void insertBatch(List<EmpExpr> exprList);

    /**
     * 根据员工的ID批量删除工作经历信息
     */
    void deleteByEmpIds(List<Integer> empIds);

    /**
     * 更新员工基本信息
     */
    void update(Emp emp);
}
