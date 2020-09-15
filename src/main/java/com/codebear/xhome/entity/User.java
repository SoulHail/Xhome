package com.codebear.xhome.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * JPA整合虚拟机数据库对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity // 告诉JPA这是一个实体类（和数据表映射的类）
@Table(name = "tb1_user") //@Table来指定和哪个数据表对应；如果省略，默认表名和类名相同user
public class User {

    @Id // 这是一个主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增主键
    private Integer id;

    @Column(name = "name",length = 32) // 这是和数据表对应的一个列
    private String name;

    @Column(name = "email",length = 32)
    private String email;
}
