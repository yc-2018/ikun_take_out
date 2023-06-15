package org.ikun.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.ikun.entity.Dish;
import org.ikun.entity.DishFlavor;

import java.util.ArrayList;
import java.util.List;

/**
 * DTO，全称为Data Transfer 0bject，即数据传输对象，一般用于展示层与服务层之间的数据传输。
 */
@Data
public class DishDto extends Dish {

    /**
     * 菜品对应的口味数据
     */
    private List<DishFlavor> flavors = new ArrayList<>();

    /**
     * 分类名称
     */
    private String categoryName;

    private Integer copies;

}
