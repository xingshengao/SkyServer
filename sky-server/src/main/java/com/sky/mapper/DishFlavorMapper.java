package com.sky.mapper;

import com.sky.entity.DishFlavor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface DishFlavorMapper {
    /**
     * 批量插入,使用动态SQL
     * @param flavors
     */
    void insertBatch(List<DishFlavor> flavors);
}
