package com.codebear.xhome.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 考勤系统用户信息实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KqUser {

    private String user_id;
    private String user_name;
    private String password;
    private String major;
    private String instructor_id;
    private String role;
}
