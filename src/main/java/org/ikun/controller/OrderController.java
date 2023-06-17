//仰晨study 创建时间2023/6/18 4:23 星期日
package org.ikun.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.ikun.common.BaseContext;
import org.ikun.common.R;
import org.ikun.entity.Orders;
import org.ikun.service.OrderDetailService;
import org.ikun.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrdersService ordersService;

    /**
     * 用户下单
     */
    @PostMapping("/submit")
    public R<String> submit(@RequestBody Orders orders) {
        log.info("订单数据:{}",orders);
        ordersService.submit(orders);

        return R.success("下单成功");
    }


    /**
     * 订单列表
     */
    @GetMapping("/userPage")
    public R<Page<Orders>> page(Integer page, Integer pageSize) {
        log.info("查询订单列表的{}页,{}大一页",page,pageSize);
        Page<Orders> pageInfo = new Page<>(page, pageSize);
        LambdaQueryWrapper<Orders> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Orders::getUserId, BaseContext.getCurrentId());

        return R.success(ordersService.page(pageInfo, wrapper));
    }
}
