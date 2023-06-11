//仰晨study 创建时间2023/6/9 16:23 星期五
package org.ikun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.ikun.dto.DishDto;
import org.ikun.entity.Dish;
import org.ikun.entity.DishFlavor;
import org.ikun.mapper.DishMapper;
import org.ikun.service.DishFlavorService;
import org.ikun.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j

public class DishImpl extends ServiceImpl<DishMapper, Dish> implements DishService{
    @Autowired
    private DishFlavorService dishFlavorService;

    /**
     * 新增菜品同时保存口味数据    因为设计多表操作  所以  要添加事务
     */
    @Transactional
    @Override
    public void saveWithFlavor(DishDto dishDto) {
        log.info("保存菜品到基本信息到菜品表dish");
        this.save(dishDto);


        log.info("保存菜品口味数据到菜品口味表dish_Flavors");
        Long dishId = dishDto.getId();
        //菜品口味
        List<DishFlavor> flavors = dishDto.getFlavors();
        flavors = flavors.stream()
                .map(item->{item.setDishId(dishId);return item;})
                .collect(Collectors.toList());
        dishFlavorService.saveBatch(flavors);
    }
}
