package com.codebear.xhome.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 考勤系统用户信息实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KqUser implements Serializable {

    private String user_id;
    private String user_name;
    private String password;
    private String major;
    private String instructor_id;
    private String role;
}
