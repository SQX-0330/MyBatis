package com.sqx.dao;

import com.sqx.bean.Employee;

/**
 * @author SQX
 * @date 2020/11/8 - 10:57
 */
public interface EmployeeDao {
    //按照员工id查询员工
    public Employee getEmpById(Integer id);
}
