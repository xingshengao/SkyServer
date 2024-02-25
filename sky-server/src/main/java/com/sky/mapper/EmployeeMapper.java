package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmployeeMapper {

    /**
     * 根据用户名查询员工
     * @param username
     * @return
     */
    @Select("select * from employee where username = #{username}")
    Employee getByUsername(String username);

    /**
     * 插入员工数据
     *
     * @param employee
     */
    // 使用注解的方式写sql语句
    @Insert("insert into employee (name, username, password, phone, sex, id_number, status, create_time, update_time, create_user, update_user) " +
            "values" +
            "(#{name},#{username},#{password}, #{phone},#{sex},#{idNumber},#{status},#{createTime},#{updateTime},#{createUser},#{updateUser})"
    )
    void insert(Employee employee);

    /**
     * 分页查询的方法
     * @param employeePageQueryDTO
     * @return
     */
    // 不使用注解了, 写到xml映射文件
    Page<Employee> pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * 根据主键来动态修改属性
     * 在mapper映射文件中编写, 而非使用注解的方式写mapper
     * @param employee
     */
    void update(Employee employee);

    /**
     * 根据ID查询, 使用注解方式写sql语句
     * mapper是持久层
     * @param id
     * @return
     */
    @Select("select * from employee where id = #{id}")
    Employee getById(Long id);
}
