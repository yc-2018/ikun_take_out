//仰晨study 创建时间2023/6/18 3:51 星期日
package org.ikun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.ikun.entity.OrderDetail;
import org.ikun.mapper.OrderDetailMapper;
import org.ikun.service.OrderDetailService;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {
}
