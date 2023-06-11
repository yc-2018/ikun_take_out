//仰晨study 创建时间2023/6/9  星期五
package org.ikun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.ikun.dto.DishDto;
import org.ikun.entity.Dish;


public interface DishService extends IService<Dish> {

    /**
     * 新增菜品，同时插入菜品对应的口味数据，需要操作两张表: dish、dish_flavor
     */
    public void saveWithFlavor(DishDto dishDto);
}
