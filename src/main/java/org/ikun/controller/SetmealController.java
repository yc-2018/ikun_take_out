//仰晨study 创建时间2023/6/13 1:26 星期二
package org.ikun.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.ikun.common.R;
import org.ikun.dto.SetmealDto;
import org.ikun.entity.Category;
import org.ikun.entity.Setmeal;
import org.ikun.service.CategoryService;
import org.ikun.service.SetmealService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 套餐管理
 */
@Slf4j
@RestController
@Api(tags = "套餐相关接口")
@RequestMapping("/setmeal")
public class SetmealController {
    @Autowired
    private SetmealService setmealService;

    @Autowired
    private CategoryService categoryService;//分类

    /**
     * 新增套餐
     */
    @PostMapping
    @ApiOperation(value = "新增套餐接口")
    @CacheEvict(value = "setmealCache",allEntries = true)    //allEntries = true 表示清除缓存中所有键对应的值,相当于清空整个缓存。
    public R<String> save(@RequestBody SetmealDto setmealDto) {
        log.info("新增套餐信息:{}", setmealDto);
        setmealService.saveWithDish(setmealDto);
        return R.success("添加套餐成功");
    }


    /**
     * 套餐分页查询
     * @param page 第几页
     * @param pageSize 一页多少条
     * @param name 是否包涵名字模糊查询
     * @return 一页套餐数据，因为本类不存在套餐分类名称所以要用到dto
     */
    @GetMapping("/page")
    @ApiOperation(value = "套餐分页查询接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",    value = "页码",     required = true),
            @ApiImplicitParam(name = "pageSize",value = "每页展示数",required = true),
            @ApiImplicitParam(name = "name",    value = "套餐名称",  required = false)
    })
    public R<Page> page(Integer page, Integer pageSize, String name) {
        //分页构造器
        Page<Setmeal> setmealPage = new Page<>(page,pageSize);
        Page<SetmealDto> dtoPage = new Page<>();

        //条件构造器
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(name != null, Setmeal::getName, name);
        queryWrapper.orderByDesc(Setmeal::getUpdateTime);

        setmealService.page(setmealPage, queryWrapper);

        //对象拷贝
        BeanUtils.copyProperties(setmealPage,dtoPage,"records");
        List<Setmeal> records = setmealPage.getRecords();
        List<SetmealDto> list = records.stream().map(item -> {
            //本身没有套餐分类名字，所以先复制到dto里面
            SetmealDto setmealDto = new SetmealDto();
            BeanUtils.copyProperties(item, setmealDto);

            //再通过分类id去才分类名字
            Long categoryId = item.getCategoryId();
            Category category = categoryService.getById(categoryId);
            if (category != null) setmealDto.setCategoryName(category.getName());
            return setmealDto;
        }).collect(Collectors.toList());
        //再把数据列表装到dto
        dtoPage.setRecords(list);

        return R.success(setmealPage);
    }

    /**
     * 批量/删除套餐
     * 不能简单就删套餐表，还有菜品关联表，还要判断是不是停售（停售才允许删）
     * @param ids 套餐id（可以多个）
     * @return 简单提示
     */
    @DeleteMapping
    @ApiOperation(value = "批量/删除套餐接口")
    @CacheEvict(value = "setmealCache",allEntries = true)
    public R<String> delete(@RequestParam("ids")List<Long> ids) {
        log.info("准备要删除的套餐id有:{}",ids);
        setmealService.removeWithDish(ids);
        return R.success("删除套餐成功");
    }

    /**
     * 根据套餐分类id给出套餐列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "套餐列表接口")
    @Cacheable(value = "setmealCache",key = "#setmeal.categoryId+'_'+ #setmeal.status")
    public R<List<Setmeal>> list(Setmeal setmeal) {
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(setmeal.getCategoryId() != null, Setmeal::getCategoryId, setmeal.getCategoryId());
        queryWrapper.eq(setmeal.getStatus() != null, Setmeal::getStatus, setmeal.getStatus());
        queryWrapper.orderByDesc(Setmeal::getUpdateTime);

        return R.success(setmealService.list(queryWrapper));
    }
}
