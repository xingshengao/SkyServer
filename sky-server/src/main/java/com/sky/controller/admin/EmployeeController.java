package com.sky.controller.admin;

import com.sky.constant.JwtClaimsConstant;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.properties.JwtProperties;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.EmployeeService;
import com.sky.utils.JwtUtil;
import com.sky.vo.EmployeeLoginVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 员工管理
 */
@RestController
@RequestMapping("/admin/employee")
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 登录
     *
     * @param employeeLoginDTO
     * @return
     */
    @PostMapping("/login")
    @ApiOperation(value = "员工登录")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        log.info("员工登录：{}", employeeLoginDTO);

        Employee employee = employeeService.login(employeeLoginDTO);

        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .userName(employee.getUsername())
                .name(employee.getName())
                .token(token)
                .build();

        return Result.success(employeeLoginVO);
    }

    /**
     * 退出
     *
     * @return
     */
    @PostMapping("/logout")
    public Result<String> logout() {
        return Result.success();
    }

    // 使用DTO接收前端数据
    @PostMapping
    @ApiOperation(value = "新增员工")
    public Result save(@RequestBody EmployeeDTO employeeDTO) {
        log.info("新增员工:{}", employeeDTO);
        employeeService.save(employeeDTO);
        return Result.success();
    }

    /**
     * 员工分页查询
     *
     * @param employeePageQueryDTO
     * @return
     */
    // 因为前端传的不是json的, 所以不需要加@RequestBody
    @GetMapping("/page")
    @ApiOperation("员工分页查询")
    public Result<PageResult> page(EmployeePageQueryDTO employeePageQueryDTO) {
        log.info("员工分页查询, 参数为:{}", employeePageQueryDTO);
        // controller调用service
        PageResult pageResult = employeeService.pageQuery(employeePageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 启用禁用员工账号
     *
     * @param status
     * @param id
     * @return
     */
    // 接受参数, 一个是路径参数(加path..注解), 另一个是id
    @PostMapping("status/{status}")
    @ApiOperation("启用禁用员工账号")
    public Result startOrStop(@PathVariable("status") Integer status, Long id) {
        log.info("启用禁用员工账号:{},{}", status, id);
        // Controller调用service
        employeeService.startOrStop(status, id);
        return Result.success();
    }

    /**
     * 查, 根绝id
     * controller控制层
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("根据Id查询员工信息")
    // 路径参数
    public Result<Employee> getById(@PathVariable Long id) {
        Employee employee = employeeService.getById(id);
        return Result.success(employee);
    }

    /**
     * 改
     * json的加RequestBody
     * @return
     */
    // 类上加路径了
    @PutMapping
    @ApiOperation("编辑员工信息")
    public Result update(@RequestBody  EmployeeDTO employeeDTO){
        log.info("编辑员工信息: {}",employeeDTO);
        employeeService.update(employeeDTO);
        return Result.success();
    }
}
