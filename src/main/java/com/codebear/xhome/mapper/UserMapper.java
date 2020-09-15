package com.codebear.xhome.mapper;

import com.codebear.xhome.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA整合虚拟机数据库，操作数据接口
 *
 * 继承JpaRepository开完成对数据库的操作
 * 泛型第一个属性：操作的实体类
 * 泛型第二个属性：实体类主键字段的类型
 */

public interface UserMapper extends JpaRepository<User,Integer> {
}
