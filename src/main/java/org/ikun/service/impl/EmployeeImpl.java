//仰晨study 创建时间2023/6/5 1:32 星期一
package org.ikun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.ikun.entity.Employee;
import org.ikun.mapper.EmployeeMapper;
import org.ikun.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
}
