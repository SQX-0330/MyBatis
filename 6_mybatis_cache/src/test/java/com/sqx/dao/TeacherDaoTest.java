package com.sqx.dao;

import com.sqx.bean.Teacher;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author SQX
 * @date 2020/11/12 - 20:20
 */
public class TeacherDaoTest {
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
            TeacherDao mapper = openSession.getMapper(TeacherDao.class);
            Teacher teacher = mapper.getTeacherById(1);
            System.out.println(teacher);
            System.out.println("==========");
            Teacher teacher1 = mapper.getTeacherById(1);
            System.out.println(teacher1);
            System.out.println(teacher1 == teacher);

        } finally {
            openSession.close();
        }

    }

    /**
     * 一级缓存失效的情况
     * 一级缓存是SqlSession级别的缓存
     * 1.不同的sqlSession使用不同的一级缓存
     *          只有在同一个sqlSession期间查询到的数据会保存到这个SqlSession的缓存中
     *          下次使用这个sqlSession查询会从缓存中拿
     * 2.同一个方法，不同的参数，由于可能之前没有查询过，所以还会发新的sql
     * 3.只要在sqlSession期间执行上一次增删改，增删改操作会清空缓存
     * 4.手动清空缓存
     *
     * 每次查询先看一级缓存中有没有，如果没有就去发送新的sql，每一个sqlSession拥有自己的一级缓存
     */
    @Test
    public void test01(){

        //1.第一个会话
        SqlSession openSession = sqlSessionFactory.openSession();

        TeacherDao mapper = openSession.getMapper(TeacherDao.class);
        Teacher teacher1 = mapper.getTeacherById(1);
        System.out.println(teacher1);

//
//        Teacher teacher3 = new Teacher();
//        teacher3.setName("sqxhhh");
//        mapper.updateTecher(teacher3);

        System.out.println("清空缓存");
//        openSession.clearCache();

        Teacher teacher2 = mapper.getTeacherById(1);
        System.out.println(teacher2);

        openSession.commit();
        openSession.close();

//        //2.第二个会话
//        SqlSession openSession2 = sqlSessionFactory.openSession();
//        TeacherDao mapper2 = openSession2.getMapper(TeacherDao.class);
//        Teacher teacher2 = mapper2.getTeacherById(1);
//        System.out.println(teacher2);


//        openSession2.close();
    }


    @Test
    public void test02(){

        //1.第一个会话
        SqlSession openSession1 = sqlSessionFactory.openSession();
        SqlSession openSession2 = sqlSessionFactory.openSession();

        TeacherDao mapper1 = openSession1.getMapper(TeacherDao.class);
        TeacherDao mapper2 = openSession2.getMapper(TeacherDao.class);

        Teacher teacher1 = mapper1.getTeacherById(1);
        System.out.println(teacher1);
        openSession1.close();
        Teacher teacher2 = mapper2.getTeacherById(1);
        System.out.println(teacher2);
        openSession2.close();

    }

    /**
     * 不会出现一级缓存和二级缓存中有同一个数据
     *      二级缓存中：一级缓存关闭了就有了
     *      一级缓存中：二级缓存中没有次数据，就看一级缓存，一级缓存没有就去查数据库；
     *                  数据库的查询后的结果一级缓存中
     *2.任何时候都是先看二级缓存、再看一级缓存，如果大家都没有就去查数据库
     */
    @Test
    public void test03(){

        //1.第一个会话
        SqlSession openSession1 = sqlSessionFactory.openSession();

        TeacherDao mapper1 = openSession1.getMapper(TeacherDao.class);
        Teacher teacher1 = mapper1.getTeacherById(1);
        System.out.println(teacher1);
        openSession1.close();

        SqlSession openSession2 = sqlSessionFactory.openSession();
        TeacherDao mapper2 = openSession2.getMapper(TeacherDao.class);
        Teacher teacher2 = mapper2.getTeacherById(1);
        Teacher teacher3 = mapper2.getTeacherById(1);
        System.out.println(teacher2);
        System.out.println(teacher3);
        openSession2.close();

    }


}