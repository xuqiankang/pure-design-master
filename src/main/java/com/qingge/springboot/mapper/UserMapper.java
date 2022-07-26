package com.qingge.springboot.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingge.springboot.controller.dto.UserPasswordDTO;
import com.qingge.springboot.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xu
 * @since 2022-07-24
 */
public interface UserMapper extends BaseMapper<User> {

    @Update("update sys_user set password = #{newPassword} where username = #{username} and password = #{password}")
    int updatePassword(UserPasswordDTO userPasswordDTO);

    Page<User> findPage(Page<User> page, @Param("username") String username, @Param("email") String email, @Param("address") String address);

    @Select("select * from sys_user  where  role = '1' AND (apply ='2' or apply is  null) and company is not null")
    List<User> getInfoList();

    @Select("select * from sys_user  where role !='0' ")
    List<User> getAllInfoList();

    @Select("select * from sys_user where apply is not null")
    List<User> getApply();

    @Update("update  sys_user set apply = #{apply} where id = #{id}")
    void updateApplyById(@Param("apply")String apply,@Param("id") String id);

    @Delete("delete from sys_user where id = #{id}")
    void deleteApplyById(String id);
}
