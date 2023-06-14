//仰晨study 创建时间2023/6/9 16:25 星期五
package org.ikun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.ikun.common.R;
import org.ikun.dto.SetmealDto;
import org.ikun.entity.Category;
import org.ikun.entity.Setmeal;
import org.ikun.entity.SetmealDish;
import org.ikun.mapper.SetmealMapper;
import org.ikun.service.CategoryService;
import org.ikun.service.SetmealDishService;
import org.ikun.service.SetmealService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SetmealImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {
    @Autowired
    private SetmealDishService setmealDishService;
    /**
     * 保存套餐和套餐与菜品的关系
     */
    @Override
    @Transactional
    public void saveWithDish(SetmealDto setmealDto) {
        //报错基本信息
        this.save(setmealDto);

        //保存套餐和菜品的关联信息
        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        setmealDishes = setmealDishes.stream().map(item->{
            item.setSetmealId(setmealDto.getId());
            return item;
        }).collect(Collectors.toList());

        setmealDishService.saveBatch(setmealDishes);
    }


}
