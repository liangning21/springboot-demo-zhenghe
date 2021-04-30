package cn.lihuan.springboot.controller.controller;


import cn.lihuan.springboot.common.annotaion.DataSource;
import cn.lihuan.springboot.common.enums.DataSourcesType;
import cn.lihuan.springboot.controller.entity.EmployeeEntity;
import cn.lihuan.springboot.controller.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lihuan
 * @since 2021-04-30
 */
@RestController
@RequestMapping("/controller/employeeEntity")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/getEmp")
    @DataSource(name = DataSourcesType.MASTER)
    public EmployeeEntity getEmp(){
        EmployeeEntity byId = employeeService.getById("1");
        return byId;
    }
}
