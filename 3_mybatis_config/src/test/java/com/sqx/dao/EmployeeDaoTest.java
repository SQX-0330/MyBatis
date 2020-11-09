package com.sqx.dao;

import com.sqx.bean.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

/**
 * @author SQX
 * @date 2020/11/8 - 15:17
 */
public class EmployeeDaoTest {
    SqlSessionFactory sqlSessionFactory = null;

    @Before
    public void initSqlSessionFactory() throws IOException {
        //1.根据全局配置文件，先得到SqlSessionFactory对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

    }

    @Test
    public void testAnnotation(){
        SqlSession openSession = sqlSessionFactory.openSession();
        EmployeeDaoAnnotation mapper = openSession.getMapper(EmployeeDaoAnnotation.class);
        Employee empById = mapper.getEmpById(1);
        System.out.println(empById);
    }

    @Test
    public void test() throws IOException {

        //1.根据全局配置文件，先得到SqlSessionFactory对象
        //2.得到SqlSession对象：
        SqlSession openSession = sqlSessionFactory.openSession();

        //3.获取dao接口的实现（映射器）
        EmployeeDao dao = openSession.getMapper(EmployeeDao.class);
        try{
            Employee employee = dao.getEmpById(1);
            System.out.println(employee);
        }finally {
            openSession.close();
        }
    }

    @Test
    public void testInsert(){
        //1.获取和数据库的一次会话
        //true自动提交
        SqlSession openSession = sqlSessionFactory.openSession(true);
        //2.获取到接口的映射器
        try {
            EmployeeDao dao = openSession.getMapper(EmployeeDao.class);

            int i = dao.insertEmployee(new Employee(null, "tomcat", "tomcat@qq.com", 0));
            System.out.println(i);
        } finally {

            //手动提交
//            openSession.commit();
            openSession.close();
        }
    }

}