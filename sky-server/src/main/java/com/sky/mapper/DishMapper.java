package com.sky.mapper;

import com.sky.annotation.AutoFill;
import com.sky.dto.DishDTO;
import com.sky.entity.Dish;
import com.sky.enumeration.OperationType;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
@Mapper

public interface DishMapper {
    /**
     * 根据ID查询菜品数量
     * @param categoryId
     * @return
     */
    @Select("select count(id) from dish where category_id = #{categoryId}")
    Integer countByCategoryId(Long categoryId);


    /**
     * 插入菜品数据
     * @param dish
     */

    @AutoFill(OperationType.INSERT) // 公共字段不需要额外赋值
    void insert(Dish dish);

}
