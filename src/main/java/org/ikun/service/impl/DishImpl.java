//仰晨study 创建时间2023/6/9 16:23 星期五
package org.ikun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.ikun.entity.Dish;
import org.ikun.mapper.DishMapper;
import org.ikun.service.DishService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DishImpl extends ServiceImpl<DishMapper, Dish> implements DishService{
}
