package com.sqx.dao;

import com.sqx.bean.Key;
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
 * @date 2020/11/10 - 19:48
 */
public class KeyDaoTest {
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
        try{
            KeyDao mapper = openSession.getMapper(KeyDao.class);
            Key key = mapper.getKeyById(1);
            System.out.println(key);
        }finally {
            openSession.close();
        }

    }

    /**
     * 分布查询
     * （0）查询钥匙的时候顺便查出锁
     * （1）Key key = keyDao.getKeyById(1);
     * （2）Lock lock = lockDao.getKeyById(1);
     */
    @Test
    public void test01() throws InterruptedException {
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            KeyDao mapper = openSession.getMapper(KeyDao.class);
            Key key = mapper.getKeyByIdSimple(2);

            //严重性能；
            System.out.println(key.getKeyName());
            Thread.sleep(2000);
            System.out.println(key.getLock().getLockName());

            //按需加载：需要的时候再去查询；全局开启按需加载策略
            //延迟加载：
        } finally {
            openSession.close();
        }
    }

}