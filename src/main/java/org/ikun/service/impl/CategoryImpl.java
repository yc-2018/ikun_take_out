//仰晨study 创建时间2023/6/9 2:24 星期五
package org.ikun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.ikun.entity.Category;
import org.ikun.mapper.CategoryMapper;
import org.ikun.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
}
