//仰晨study 创建时间2023/6/18 3:45 星期日
package org.ikun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.ikun.entity.Orders;

public interface OrdersService extends IService<Orders> {

    /**
     * 用户下单
     */
    public void submit(Orders orders);
}
