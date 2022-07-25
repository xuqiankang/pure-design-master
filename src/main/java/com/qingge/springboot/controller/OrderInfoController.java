package com.qingge.springboot.controller;


import com.qingge.springboot.common.Constants;
import com.qingge.springboot.common.Result;
import com.qingge.springboot.entity.OrderInfo;
import com.qingge.springboot.service.IUserService;
import com.qingge.springboot.service.OrderInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xu
 * @since 2022-07-24
 */
@RestController
@RequestMapping("/order")
public class OrderInfoController {

    @Resource
    private IUserService userService;

    @Resource
    private OrderInfoService orderInfoService;

    @GetMapping("/getOrderInfo/{username}")
    public Result getOrderInfo(@PathVariable String username) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setVisitAdmin(username);
        if (username != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(username);
            m.replaceAll("");
        } else {
            return Result.error(Constants.CODE_400, "参数错误");
        }
        return Result.success(orderInfoService.getByVisitAdmin(orderInfo));
    }

    @GetMapping("/getOrderInfoPer/{username}")
    public Result getOrderInfoPer(@PathVariable String username) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setName(username);
        if (username != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(username);
            m.replaceAll("");
        } else {
            return Result.error(Constants.CODE_400, "参数错误");
        }
        return Result.success(orderInfoService.getByName(orderInfo));
    }

    @PostMapping("/addOrder")
    public Result register(@RequestBody OrderInfo orderInfo) {
        return Result.success(orderInfoService.addOrder(orderInfo));
    }

    // 新增或者更新
    @PostMapping("/save")
    public Result save(@RequestBody OrderInfo orderInfo) {
        return Result.success(orderInfoService.saveOrUpdate(orderInfo));
    }

    // 刪除
    @GetMapping("/deleteOrder/{id}")
    public Result deleteOrder(@PathVariable String id) {
        orderInfoService.deleteOrder(id);
        return Result.success();
    }
}

