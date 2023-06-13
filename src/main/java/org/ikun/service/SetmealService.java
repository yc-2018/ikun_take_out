//仰晨study 创建时间2023/6/9  星期五
package org.ikun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.ikun.dto.SetmealDto;
import org.ikun.entity.Setmeal;


public interface SetmealService extends IService<Setmeal> {
    /**
     * 保存套餐和套餐与菜品的关系
     */
    public void saveWithDish(SetmealDto setmealDto);
}
