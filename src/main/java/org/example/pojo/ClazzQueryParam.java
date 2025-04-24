package org.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClazzQueryParam {
    private Integer page = 1;
    private Integer pageSize = 5;
    private String name;
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate begin;
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate end;
    private LocalDateTime currentDate = LocalDateTime.now(); //当前时间
}
