//仰晨study 创建时间2023/6/9 2:27 星期五
package org.ikun.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.ikun.common.R;
import org.ikun.entity.Category;
import org.ikun.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@Slf4j
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 新增分类
     */
    @PostMapping
    public R<String> save(@RequestBody Category category) {
        log.info("提交分类:{}",category);

        categoryService.save(category);
        return R.success("新增分类成功");
    }

    /**
     * 分类的分页查询
     * @param page 第几页
     * @param pageSize 一页多大
     * @return 分页类列表
     */
    @GetMapping("/page")
    public R<Page<Category>> page(Integer page, Integer pageSize) {
        log.info("分类的分页查询第{}页,每页{}条",page,pageSize);
        //分页构造器
        Page<Category> pageInfo = new Page<>(page, pageSize);

        //条件构造器
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        //添加排序字段，根据sort进行排序
        queryWrapper.orderByAsc(Category::getSort);

        //分页查询
        categoryService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }
}
