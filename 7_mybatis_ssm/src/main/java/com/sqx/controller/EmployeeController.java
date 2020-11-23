package com.sqx.controller;

import com.sqx.bean.Employee;
import com.sqx.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * @author SQX
 * @date 2020/11/18 - 17:09
 */
@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @RequestMapping("/emps")
    public String emps(Map<String, Object> map){
        List<Employee> emps = employeeService.getEmps();
        map.put("allEmps", emps);
        return "list";
    }
}
