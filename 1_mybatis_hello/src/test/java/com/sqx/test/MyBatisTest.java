package com.sqx.test;

import com.sqx.bean.Employee;
import com.sqx.dao.EmployeeDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisTest {

    @Test
    public void test01() throws IOException {
        //1.根据全局配置文件创建一个SqlSessionFactory
        //SqlSessionFactory：是SqlSession工厂，负责创建SqlSession对象
        //SqlSession：sql会话（代表和数据库的以此会话）
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        Employee employee = null;
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            //2.获取和数据库的一次会话:getConnection();

            //3.使用SqlSession操作数据库,获取到dao接口的实现类
            EmployeeDao employeeDao = openSession.getMapper(EmployeeDao.class);
            employee = employeeDao.getEmpById(1);
        }finally {
            openSession.close();
        }
        System.out.println(employee);
    }
}
