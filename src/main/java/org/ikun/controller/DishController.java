//仰晨study 创建时间2023/6/10 3:03 星期六
package org.ikun.controller;

import lombok.extern.slf4j.Slf4j;
import org.ikun.common.R;
import org.ikun.dto.DishDto;
import org.ikun.service.DishFlavorService;
import org.ikun.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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
    private DishFlavorService dishFlavorService;


    /**
     * 新增菜品
     * @param dishDto 菜品实体类没有口味  所以要用到dto
     * @return 成功信息
     */
    @PostMapping
    public R<String> save(@RequestBody DishDto dishDto) {
        log.info("添加菜品啦:{}",dishDto);
        dishService.saveWithFlavor(dishDto);
        return R.success("添加菜品成功");
    }

}
