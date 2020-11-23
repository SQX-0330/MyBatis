package com.sqx.dao;

import com.sqx.bean.Employee;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author SQX
 * @date 2020/11/8 - 14:59
 */
public interface EmployeeDao {

    //列名作为key，数据作为值
    public Map<String, Object> getEmpByIdReturnMap(Integer id);

    /**
     * key就是这个记录的主键，value就是这条记录封装好的对象
     * @return
     *
     * 把查出的id作为key
     */
    @MapKey("id")
    public Map<String, Employee> getAllEmpsReturnMap();

    public List<Employee> getAllEmps();

    public Employee getEmpById(Integer id);

    public Employee getEmpByIdAndEmpName(@Param("id")Integer id, @Param("empName") String empName);

    public Employee getEmployeeByIdAndEmpName(Map<String, Object> map);

    public int updateEmployee(Employee employee);

    public int deleteEmployee(Integer id);

    public int insertEmployee(Employee employee);

    public int insertEmployee2(Employee employee);
}
