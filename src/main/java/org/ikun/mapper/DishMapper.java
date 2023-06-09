//仰晨study 创建时间2023/6/9 16:19 星期五
package org.ikun.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.ikun.entity.Dish;
/**菜品mapper*/
@Mapper
public interface DishMapper extends BaseMapper<Dish> {
}
