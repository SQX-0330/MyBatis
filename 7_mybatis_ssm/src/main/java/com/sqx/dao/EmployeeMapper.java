package com.sqx.dao;

import com.sqx.bean.Employee;

import java.util.List;


/**
 * @author SQX
 * @date 2020/11/8 - 14:59
 */
public interface EmployeeMapper {

    public Employee getEmpById(Integer id);

    public List<Employee> getEmps();
}
