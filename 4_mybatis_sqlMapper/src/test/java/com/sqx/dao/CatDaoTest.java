package com.sqx.dao;

import com.sqx.bean.Cat;
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
 * @date 2020/11/10 - 18:40
 */
public class CatDaoTest {

    SqlSessionFactory sqlSessionFactory = null;

    @Before
    public void initSqlSeesionFactory() throws IOException {
        //1.根据全局配置文件，先得到SqlSessionFactory对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }


    /**
     * 默认mybatis自动封装结果对象
     * 1.按照列名和属性名一一对应的规则（不区分大小写）
     * 2.如果不一一对应：
     *          （1）开启驼峰命名法（满足驼峰命名规则aaa_bbb  aaaBbb）
     *          （2）起别名
     */
    @Test
    public void test(){
        SqlSession openSession = sqlSessionFactory.openSession();
        try{
            CatDao mapper = openSession.getMapper(CatDao.class);
            Cat catById = mapper.getCatById(1);
            System.out.println(catById);
        }finally {
            openSession.close();
        }
    }
}