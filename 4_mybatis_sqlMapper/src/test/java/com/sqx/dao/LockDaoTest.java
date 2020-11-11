package com.sqx.dao;

import com.sqx.bean.Key;
import com.sqx.bean.Lock;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLOutput;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author SQX
 * @date 2020/11/11 - 16:21
 */
public class LockDaoTest {
    SqlSessionFactory sqlSessionFactory = null;

    @Before
    public void initSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }



    @Test
    public void test(){
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            LockDao mapper = openSession.getMapper(LockDao.class);
            Lock lock = mapper.getLockById(2);
            System.out.println(lock);
            System.out.println("所有锁如下：");
            List<Key> keys = lock.getKeys();
            for (Key key : keys) {
                System.out.println(key);
            }
        } finally {
            openSession.close();
        }

    }

    /**
     * 一般在工作的时候，写成两个方法：
     *
     * public Key getKeySimple(Integer id);
     *
     * 推荐都来写连接查询
     * public Key getKeyAssociate();
     */
    @Test
    public void test01(){
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            LockDao mapper = openSession.getMapper(LockDao.class);
            Lock lock = mapper.getLockById(2);
            System.out.println(lock.getLockName());
            System.out.println("所有锁如下：");
            List<Key> keys = lock.getKeys();
            for (Key key : keys) {
                System.out.println(key);
            }
        } finally {
            openSession.close();
        }

    }

}