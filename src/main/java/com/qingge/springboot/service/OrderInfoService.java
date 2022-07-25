package com.qingge.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qingge.springboot.common.Result;
import com.qingge.springboot.entity.OrderInfo;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xu
 * @since 2022-07-24
 */
public interface OrderInfoService extends IService<OrderInfo> {

    Result getByVisitAdmin(OrderInfo orderInfo);

    Result getByName(OrderInfo orderInfo);

    OrderInfo addOrder(OrderInfo orderInfo);
}
