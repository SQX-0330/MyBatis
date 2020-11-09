package com.sqx.dao;

import com.sqx.bean.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @author SQX
 * @date 2020/11/8 - 14:59
 */
public interface EmployeeDao {

    public Employee getEmpById(Integer id);

    public Employee getEmpByIdAndEmpName(@Param("id")Integer id, @Param("empName") String empName);
    public Employee getEmployeeByIdAndEmpName(Map<String, Object> map);

    public int updateEmployee(Employee employee);

    public int deleteEmployee(Integer id);

    public int insertEmployee(Employee employee);

    public int insertEmployee2(Employee employee);
}
