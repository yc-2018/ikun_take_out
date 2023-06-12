//仰晨study 创建时间2023/6/13 1:26 星期二
package org.ikun.controller;

import lombok.extern.slf4j.Slf4j;
import org.ikun.service.SetmealDishService;
import org.ikun.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequestMapping("/setmealdish")
public class SetmealDishController {
    @Autowired
    private SetmealDishService setmealDishService;

    @Autowired
    private SetmealService setmealService;

}
