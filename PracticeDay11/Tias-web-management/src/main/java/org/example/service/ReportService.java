package org.example.service;

import org.apache.ibatis.annotations.Mapper;
import org.example.pojo.JobOption;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReportService {
    /**
     * 统计各个职位的员工人数
     * @return
     */
    JobOption getEmpJobData();

    /**
     * 统计员工性别信息
     */
    List<Map> getEmpGenderData();
}