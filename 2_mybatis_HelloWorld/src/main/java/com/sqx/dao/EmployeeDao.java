package com.sqx.dao;

import com.sqx.bean.Employee;

/**
 * @author SQX
 * @date 2020/11/8 - 14:59
 */
public interface EmployeeDao {

    public Employee getEmpById(Integer id);

    public int updateEmployee(Employee employee);

    public int deleteEmployee(Integer id);

    public int insertEmployee(Employee employee);
}
