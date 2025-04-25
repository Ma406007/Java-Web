package org.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentQueryParam {
    private Integer page = 1;//分页查询的页码
    private Integer pageSize = 10;//分页查询的每页记录数
    private String name;//姓名
    private Integer degree;//学历(1:初中 2:高中 3:大专 4:本科 5:硕士 6:博士)
    private Integer clazzId;//班级ID
}
