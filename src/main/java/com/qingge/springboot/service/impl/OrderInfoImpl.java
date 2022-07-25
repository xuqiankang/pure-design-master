package com.qingge.springboot.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qingge.springboot.common.Constants;
import com.qingge.springboot.common.Result;
import com.qingge.springboot.entity.OrderInfo;
import com.qingge.springboot.exception.ServiceException;
import com.qingge.springboot.mapper.OrderInfoMapper;
import com.qingge.springboot.service.OrderInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xu
 * @since 2022-07-24
 */
@Service
public class OrderInfoImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements OrderInfoService {

    private static final Log LOG = Log.get();

    @Resource
    private OrderInfoMapper orderInfoMapper;

    @Override
    public Result getByVisitAdmin(OrderInfo one) {
        if (one != null) {
            QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("visit_admin", one.getVisitAdmin());
            List<OrderInfo>orderInfos =  orderInfoMapper.getListByVisitAdmin(one);
            return Result.success(orderInfos);
        } else {
            throw new ServiceException(Constants.CODE_400, "参数异常");
        }
    }

    @Override
    public Result getByName(OrderInfo one) {
        if (one != null) {
            QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("name", one.getName());
            List<OrderInfo>orderInfos =  orderInfoMapper.getListByVisitAdmin(one);
            return Result.success(orderInfos);
        } else {
            throw new ServiceException(Constants.CODE_400, "参数异常");
        }
    }

    @Override
    public OrderInfo addOrder(OrderInfo orderInfo) {
        OrderInfo one = new OrderInfo();
        BeanUtil.copyProperties(orderInfo, one, true);
        save(one);  // 把 copy完之后的用户对象存储到数据库
        return one;
    }

    private OrderInfo getOrderInfo(OrderInfo orderInfo) {
        QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", orderInfo.getId());
        OrderInfo one;
        try {
            one = getOne(queryWrapper); // 从数据库查询用户信息
        } catch (Exception e) {
            LOG.error(e);
            throw new ServiceException(Constants.CODE_500, "系统错误");
        }
        return one;
    }
}
