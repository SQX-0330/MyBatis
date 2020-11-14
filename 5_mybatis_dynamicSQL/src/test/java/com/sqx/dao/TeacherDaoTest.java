package com.sqx.dao;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import com.sqx.bean.Teacher;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;
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
//            Teacher teacher2 = new Teacher();
//            teacher2.setId(1);
//            teacher2.setName("%a%");
//            teacher2.setBirth(new Date());
//            List<Teacher> teacherByCondition = mapper.getTeacherByCondition(teacher2);
//            System.out.println(teacherByCondition);

//            Teacher teacher = mapper.getTeacherById(1);

//            List<Teacher> teacherByIdIn = mapper.getTeacherByIdIn(Arrays.asList(1, 2, 3, 4, 5));
//            System.out.println(teacherByIdIn);

            Teacher teacher2 = new Teacher();
//            teacher2.setId(1);
//            teacher2.setName("admin");
            List<Teacher> teacher = mapper.getTeacherByConditionChoose(teacher2);
            System.out.println(teacher);

        } finally {
            openSession.close();
        }

    }


    @Test
    public void test01(){
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            TeacherDao mapper = openSession.getMapper(TeacherDao.class);
            Teacher teacher2 = new Teacher();
            teacher2.setId(1);
            teacher2.setName("lala");
            int i = mapper.updateTecher(teacher2);
            System.out.println(i);
            Teacher teacher = mapper.getTeacherById(1);
            System.out.println(teacher);

        } finally {
            openSession.close();
        }

    }


}