package org.ikun.dto;


import lombok.Data;
import org.ikun.entity.Setmeal;
import org.ikun.entity.SetmealDish;

import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
