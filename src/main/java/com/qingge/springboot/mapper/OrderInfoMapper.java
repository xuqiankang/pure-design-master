package com.qingge.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingge.springboot.controller.dto.UserPasswordDTO;
import com.qingge.springboot.entity.OrderInfo;
import com.qingge.springboot.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xu
 * @since 2022-07-24
 */
public interface OrderInfoMapper extends BaseMapper<OrderInfo> {
    @Select("select * from  order_info where visit_admin = trim(#{visitAdmin}) order by time desc ")
    List<OrderInfo> getListByVisitAdmin(OrderInfo one);

    @Select("select * from  order_info where name = trim(#{name}) order by time desc")
    List<OrderInfo> getByName(OrderInfo one);

//    @Update("update sys_user set password = #{newPassword} where username = #{username} and password = #{password}")
//    int updatePassword(UserPasswordDTO userPasswordDTO);

//    Page<OrderInfo> findPage(Page<OrderInfo> page, @Param("username") String username, @Param("email") String email, @Param("address") String address);
}
