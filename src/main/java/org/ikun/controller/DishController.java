//仰晨study 创建时间2023/6/10 3:03 星期六
package org.ikun.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.ikun.common.R;
import org.ikun.dto.DishDto;
import org.ikun.entity.Category;
import org.ikun.entity.Dish;
import org.ikun.entity.DishFlavor;
import org.ikun.service.CategoryService;
import org.ikun.service.DishFlavorService;
import org.ikun.service.DishService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


/**
 * 菜品和菜品口味控制层
 */
@RestController
@RequestMapping("/dish")
@Slf4j
public class DishController {
    @Autowired
    private DishService dishService;
    @Autowired
    public CategoryService categoryService;
    @Autowired
    public DishFlavorService dishFlavorService;
    @Autowired
    public RedisTemplate redisTemplate;


    /**
     * 新增菜品
     * @param dishDto 菜品实体类没有口味  所以要用到dto
     * @return 成功信息
     */
    @PostMapping
    public R<String> save(@RequestBody DishDto dishDto) {
        log.info("添加菜品啦:{}",dishDto);
        dishService.saveWithFlavor(dishDto);


        //清除所有菜品的缓村
        //Set keys = redisTemplate.keys("dish_*");
        //redisTemplate.delete(keys);

        //清除单个菜品分类下的缓存
        String key = "dish_" + dishDto.getCategoryId() + "_1";
        redisTemplate.delete(key);


        return R.success("添加菜品成功");
    }


    /**
     * 菜品信息的分页请求
     * @param page 第几页
     * @param pageSize 每页几条
     * @param name 是否带名称查询
     * @return 列表
     */
    @GetMapping("/page")
    public R<Page> page(Integer page, Integer pageSize, String name) {
        //构造分页构造器对象
        Page<Dish> pageInfo = new Page<>(page,pageSize);
        Page<DishDto> dishDtopage = new Page<>();

        //条件构造器
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(name != null, Dish::getName, name);
        queryWrapper.orderByDesc(Dish::getUpdateTime);

        //执行分页查询
        dishService.page(pageInfo, queryWrapper);

        //对象拷贝----把pageInfo的属性值拷贝到dishDtoPage但是不拷贝"records"属性
        BeanUtils.copyProperties(pageInfo,dishDtopage,"records");

        List<Dish> records = pageInfo.getRecords();

        //对records属性进行处理，本类是dish然后变成dishDto就有分类名字属性，然后查表赋值
        List<DishDto> list = records.stream().map(item -> {
            DishDto dishDto = new DishDto();
            BeanUtils.copyProperties(item, dishDto);
            Long categoryId = item.getCategoryId(); //分类ID
            //根据ID查询分类对象
            Category category = categoryService.getById(categoryId);
            if (category!=null) dishDto.setCategoryName(category.getName());
            return dishDto;
        }).collect(Collectors.toList());

        //给上面没拷贝的records属性赋值（records属性在Page这个类里面就是实体类数据）
        dishDtopage.setRecords(list);

        return R.success(dishDtopage);
    }

    /**
     * @param id 菜品id
     * @return 菜品和口味信息
     */
    @GetMapping("/{id}")
    public R<DishDto> get(@PathVariable Long id) {
        DishDto dishDto = dishService.getByIdWithFlavor(id);

        return R.success(dishDto);
    }

    /**
     * 新增菜品
     * @param dishDto 菜品实体类没有口味  所以要用到dto
     * @return 成功信息
     */
    @PutMapping
    public R<String> update(@RequestBody DishDto dishDto) {
        log.info("修改菜品啦:{}",dishDto);
        dishService.updateWithFlavor(dishDto);

        //清除所有菜品的缓村
        //Set keys = redisTemplate.keys("dish_*");
        //redisTemplate.delete(keys);

        //清除单个菜品分类下的缓存
        String key = "dish_" + dishDto.getCategoryId() + "_1";
        redisTemplate.delete(key);


        return R.success("修改菜品成功");
    }


//    /**
//     * 获取菜品分类到套餐页面
//     * @param dish 只要拿分类id   # 应该还有名字
//     * @return 菜品列表
//     */
//    @GetMapping("/list")
//    public R<List<Dish>> list(Dish dish) {
//        //条件构造器
//        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(dish.getCategoryId() != null, Dish::getCategoryId, dish.getCategoryId());
//        //按名字搜索?
//        queryWrapper.like(dish.getName() != null, Dish::getName, dish.getName());
//        queryWrapper.eq(Dish::getStatus, 1);//只查询状态为1的（起售 的菜品)
//        queryWrapper.orderByAsc(Dish::getSort).orderByDesc(Dish::getUpdateTime);
//
//        List<Dish> list = dishService.list(queryWrapper);
//
//        return R.success(list);
//    }

    /**
     * 获取菜品分类到套餐页面
     * @param dish 只要拿分类id   # 应该还有名字
     * @return 菜品列表
     */
    @GetMapping("/list")
    public R<List<DishDto>> list(Dish dish) {
        List<DishDto> dishDtoList = null;

        //动态构造key
        String key = "dish_" + dish.getCategoryId() + "_" + dish.getStatus();

        //先从Redis缓存中获取数据
        dishDtoList = (List<DishDto>) redisTemplate.opsForValue().get(key);
        //如果 redis 存在。直接返回，无需查询数据库。
        if (dishDtoList!=null)
            return R.success(dishDtoList);

        //条件构造器
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(dish.getCategoryId() != null, Dish::getCategoryId, dish.getCategoryId());
        //按名字搜索?
        queryWrapper.like(dish.getName() != null, Dish::getName, dish.getName());
        queryWrapper.eq(Dish::getStatus, 1);//只查询状态为1的（起售 的菜品)
        queryWrapper.orderByAsc(Dish::getSort).orderByDesc(Dish::getUpdateTime);

        List<Dish> list = dishService.list(queryWrapper);

        dishDtoList = list.stream().map(item -> {
            DishDto dishDto = new DishDto();
            BeanUtils.copyProperties(item, dishDto);
            Long categoryId = item.getCategoryId(); //分类ID
            //根据ID查询分类对象
            Category category = categoryService.getById(categoryId);
            if (category!=null) dishDto.setCategoryName(category.getName());

            //查询口味
            Long dishId =  item.getId();
            LambdaQueryWrapper<DishFlavor> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(DishFlavor::getDishId, dishId);
            //SQL:select * from dish_flavor where dish_id = ?
            List<DishFlavor> dishFlavorList = dishFlavorService.list(lambdaQueryWrapper);
            if(dishFlavorList!=null) dishDto.setFlavors(dishFlavorList);

            return dishDto;
        }).collect(Collectors.toList());

        //如果不存在，需要查询数据库将查询到的菜品数据缓存到 redis
        redisTemplate.opsForValue().set(key,dishDtoList,60, TimeUnit.MINUTES);

        return R.success(dishDtoList);
    }

}
