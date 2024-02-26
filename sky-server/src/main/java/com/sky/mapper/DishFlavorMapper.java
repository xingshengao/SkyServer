package com.sky.mapper;

import com.sky.entity.DishFlavor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface DishFlavorMapper {
    /**
     * 批量插入,使用动态SQL
     * @param flavors
     */
    void insertBatch(List<DishFlavor> flavors);

    /**
     * 根据菜品ID删除对应的口味数据
     * @param dishId
     */
    @Delete("delete from dish_flavor where dish_id = #{dishId}")
    void deleteByDishId(Long dishId);

    /**
     * 根据菜品ID集合批量删除关联的口味数据
     * @param DishIds
     */
    void deleteByDishIds(List<Long> DishIds);

    /**
     * 根据菜品ID查询对应的口味数据
     * @param dishId
     * @return
     */

    @Select("select * from dish_flavor where dish_id = #{dishId}")
    List<DishFlavor> getByDishId(Long dishId);
}
