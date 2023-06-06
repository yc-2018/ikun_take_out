//仰晨study 创建时间2023/6/5 1:27 星期一
package org.ikun.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.ikun.entity.Employee;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
