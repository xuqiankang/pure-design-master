package com.qingge.springboot.controller;


import com.qingge.springboot.common.Result;
import com.qingge.springboot.entity.OrderInfo;
import com.qingge.springboot.service.IUserService;
import com.qingge.springboot.service.OrderInfoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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

    @PostMapping("/getOrderInfo")
    public Result getOrderInfo(@RequestBody String username) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setVisitAdmin(username);
        return Result.success(orderInfoService.getByParams(orderInfo));
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
//
//    /**
//     * 修改密码
//     * @param userPasswordDTO
//     * @return
//     */
//    @PostMapping("/password")
//    public Result password(@RequestBody UserPasswordDTO userPasswordDTO) {
//        userService.updatePassword(userPasswordDTO);
//        return Result.success();
//    }
//
//    @DeleteMapping("/{id}")
//    public Result delete(@PathVariable Integer id) {
//        return Result.success(userService.removeById(id));
//    }
//
//    @PostMapping("/del/batch")
//    public Result deleteBatch(@RequestBody List<Integer> ids) {
//        return Result.success(userService.removeByIds(ids));
//    }
//
//    @GetMapping
//    public Result findAll() {
//        return Result.success(userService.list());
//    }
//
//    @GetMapping("/role/{role}")
//    public Result findUsersByRole(@PathVariable String role) {
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("role", role);
//        List<User> list = userService.list(queryWrapper);
//        return Result.success(list);
//    }
//
//    @GetMapping("/{id}")
//    public Result findOne(@PathVariable Integer id) {
//        return Result.success(userService.getById(id));
//    }
//
//    @GetMapping("/username/{username}")
//    public Result findByUsername(@PathVariable String username) {
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("username", username);
//        return Result.success(userService.getOne(queryWrapper));
//    }
//
//    @GetMapping("/page")
//    public Result findPage(@RequestParam Integer pageNum,
//                               @RequestParam Integer pageSize,
//                               @RequestParam(defaultValue = "") String username,
//                               @RequestParam(defaultValue = "") String email,
//                               @RequestParam(defaultValue = "") String address) {
//        return Result.success(userService.findPage(new Page<>(pageNum, pageSize), username, email, address));
//    }

}

