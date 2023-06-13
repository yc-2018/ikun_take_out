//仰晨study 创建时间2023/6/13 1:26 星期二
package org.ikun.controller;

import lombok.extern.slf4j.Slf4j;
import org.ikun.common.R;
import org.ikun.dto.SetmealDto;
import org.ikun.service.SetmealDishService;
import org.ikun.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 套餐管理
 */
@Slf4j
@RestController
@RequestMapping("/setmeal")
public class SetmealController {

    @Autowired
    private SetmealDishService setmealDishService;

    @Autowired
    private SetmealService setmealService;

    /**
     * 新增套餐
     */
    @PostMapping
    public R<String> save(@RequestBody SetmealDto setmealDto) {
        log.info("新增套餐信息:{}", setmealDto);
        setmealService.saveWithDish(setmealDto);
        return R.success("添加套餐成功");
    }

}
