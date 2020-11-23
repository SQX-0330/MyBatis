import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sqx.bean.Employee;
import com.sqx.dao.EmployeeDao;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyBatisTest {

    public SqlSessionFactory init() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory;
    }

    @Test
    public void test01() throws IOException {
        SqlSessionFactory sqlSessionFactory = init();
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

    @Test
    public void test02() throws IOException {
        SqlSessionFactory sqlSessionFactory = init();
        Employee employee = null;
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            //2.获取和数据库的一次会话:getConnection();

            //3.使用SqlSession操作数据库,获取到dao接口的实现类
            EmployeeDao employeeDao = openSession.getMapper(EmployeeDao.class);
            Page<Object> page = PageHelper.startPage(3, 2);
            List<Employee> emps = employeeDao.getEmps();

            //连续显示多少页
            PageInfo<Employee> info = new PageInfo<>(emps, 2);

            for (Employee emp : emps) {
                System.out.println(emp);

            }
//            System.out.println("当前页码:" + page.getPageNum());
//            System.out.println("总记录数:" + page.getTotal());
//            System.out.println("每页的记录数:" + page.getPageSize());
//            System.out.println("总页码:" + page.getPages());

            System.out.println("当前页码:" + info.getPageNum());
            System.out.println("总记录数:" + info.getTotal());
            System.out.println("每页的记录数:" + info.getPageSize());
            System.out.println("总页码:" + info.getPages());
            System.out.println("是否第一页:" + info.isIsFirstPage());

            int[] nums = info.getNavigatepageNums();
            for (int i = 0; i < nums.length; i++) {
                System.out.println(nums[i]);
            }
        }finally {
            openSession.close();
        }

    }
}
