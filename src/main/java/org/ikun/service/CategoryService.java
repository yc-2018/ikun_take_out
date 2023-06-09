//仰晨study 创建时间2023/6/9 2:23 星期五
package org.ikun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.ikun.entity.Category;
import org.springframework.stereotype.Service;


public interface CategoryService extends IService<Category> {
    public void remove(Long id);
}
