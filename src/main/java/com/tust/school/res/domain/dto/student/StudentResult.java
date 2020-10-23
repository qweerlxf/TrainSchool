package com.tust.school.res.domain.dto.student;

import lombok.Data;

@Data
public class StudentResult {

    /**
     * id
     */
    private Integer id;

    /**
     * 编号
     */
    private String no;

    /**
     * 名称
     */
    private String name;


    private Integer status;
    private String statusStr;
}
