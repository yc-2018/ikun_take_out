//仰晨study 创建时间2023/6/9 2:24 星期五
package org.ikun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.ikun.common.CustomException;
import org.ikun.entity.Category;
import org.ikun.entity.Dish;
import org.ikun.entity.Setmeal;
import org.ikun.mapper.CategoryMapper;
import org.ikun.service.CategoryService;
import org.ikun.service.DishService;
import org.ikun.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    /**
     * 菜品
     */
    @Autowired
    private DishService dishService;
    /**
     * 套餐
     */
    @Autowired
    private SetmealService setmealService;
    /**
     * 根据id删除分类删除之前要进行判读该分类是否已经关联菜品
     * @param id 分类id
     */
    @Override
    public void remove(Long id) {
        //查询当前分类下是否关联了菜品，如果关联了就直接抛出一个业务异常
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //添加查询条件，根据id分类进行查询
        dishLambdaQueryWrapper.eq(Dish::getCategoryId, id);
        int dishCount = dishService.count(dishLambdaQueryWrapper);
        if (dishCount > 0) {
            //关联了，抛异常
            throw new CustomException("当前分类下关联了菜品,不能删除");
        }

        //查询当前分类下是否关联了套餐，如果关联了就直接抛出一个业务异常
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId, id);
        int setmealCount = setmealService.count(setmealLambdaQueryWrapper);
        if (setmealCount > 0) {
            //关联了，抛异常
            throw new CustomException("当前分类下关联了套餐,不能删除");
        }

        //正常删除分类
        super.removeById(id);
    }
}
