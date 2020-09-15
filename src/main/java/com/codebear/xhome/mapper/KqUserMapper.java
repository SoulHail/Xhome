package com.codebear.xhome.mapper;

import com.codebear.xhome.entity.KqUser;
import org.apache.ibatis.annotations.*;

@Mapper
public interface KqUserMapper {

    @Select("select * from at_user where user_id = #{id}")
    KqUser getKqUser(String id);

    @Delete("delete from at_user where user_id = #{id}")
    int deleteKqUser(String id);

    @Insert("insert into at_user values(#{user_id},#{user_name},#{password},#{major},#{instructor_id},#{role})")
    int insertKqUser(KqUser kqUser);

    @Update("update at_user set user_name = 'dsdfdsf' where user_id = #{id}")
    int updateKqUser(String id);

}
