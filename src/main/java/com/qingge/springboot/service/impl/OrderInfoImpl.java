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
import java.util.Date;
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
            setStatus(orderInfos);
            return Result.success(orderInfos);
        } else {
            throw new ServiceException(Constants.CODE_400, "参数异常");
        }
    }

    private void setStatus(List<OrderInfo> orderInfos) {
        for (OrderInfo orderInfo : orderInfos) {
            if("2".equals(orderInfo.getStatus())){
                int abs = Math.abs((int) ((new Date()).getTime() - orderInfo.getTime().getTime()) / (1000 * 3600 * 24));
                if(abs>=1){
                    orderInfo.setStatus("5");
                }
            }
        }
    }

    @Override
    public Result getByName(OrderInfo one) {
        if (one != null) {
            QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("name", one.getName());
            List<OrderInfo>orderInfos =  orderInfoMapper.getByName(one);
            setStatus(orderInfos);
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

    @Override
    public void deleteOrder(String id) {
        orderInfoMapper.deleteById(id);
    }
}
