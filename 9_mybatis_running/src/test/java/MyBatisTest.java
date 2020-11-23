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

    /**
     * 1.获取sqlSessionFactory对象
     *          解析文件的每一个信息保存在Configuration中，返回包含Configuration的DefaultSqlSession
     *          注意：MappedStatement：代表一个增删改查的详细信息
     *
     *  2.获取sqlSession对象
     *      返回一个DefaultSQLsession对象，包含Executor和Configuration
     *      这一步会创建一个Executor
     *  3.获取接口的动态代理（MapperProxy）
     *  getMapper，使用MapperProxyFactory创建一个MapperProxy的代理对象
     *  代理对象里面包含了，DefaultSqlSession(Executor )
     *  4.执行增删改查方法
     *   1.根据配置文件（全局，sql映射）初始化Configuration对象
     *   2.创建啊一个defaultSqlSession对象
     *     里面包含了Configuration以及Executor（根据全局配置文件中的defaultExecutorType创建出对应的Excutor）
     *   3.DefaultSqlSession.getMapper（）：拿到Mapper接口对应的MapperProxy
     *   4.MapperProxy里面有（DefaultSqlSession）
     *   5.执行增删改查方法：
     *          1.调用DefaultSqlSession的增删改查（Executor）
     *          2.会创建一个StatementHandler对象
     *                  （同时创建一个ParameterHandler和ResultSetHandler）
     *          3.调用StatementHandler预编译参数以及设置参数值
     *                 使用ParameterHandler来给sql设置参数
     *          4.调用StatementHandler的增删改查方法
     *          5.ResultSetHandler封装结果
     *     注意：四大对象创建的时候都会有一个interceptorChain.pluginAll()
     *
     */

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
