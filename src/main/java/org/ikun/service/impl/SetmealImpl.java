//仰晨study 创建时间2023/6/9 16:25 星期五
package org.ikun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.ikun.entity.Setmeal;
import org.ikun.mapper.SetmealMapper;
import org.ikun.service.SetmealService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SetmealImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {
}
