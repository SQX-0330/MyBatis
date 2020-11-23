
import com.sqx.bean.Employee;
import com.sqx.bean.EmployeeExample;
import com.sqx.dao.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author SQX
 * @date 2020/11/19 - 16:58
 */
public class Mybatis {

    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void testMbg() throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException {
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        File configFile = new File("src/mbg.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }

//    @Test
//    public void testMybatis3Simple() throws IOException {
//        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
//        SqlSession openSession = sqlSessionFactory.openSession();
//        try {
//            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
//            List<Employee> employees = mapper.selectAll();
//            for (Employee employee : employees) {
//                System.out.println(employee);
//            }
//        } finally {
//            openSession.close();
//        }
//
//    }


    @Test
    public void testMybatis3() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);

            //xxxExample就额是封装查询条件的
            //1.查询所有
            List<Employee> employees = mapper.selectByExample(null);

            //2.查询员工名字中由e字母的，和员工性别是1的
            EmployeeExample employeeExample = new EmployeeExample();
            //Criteria就是拼装查询条件

            //select id, empname, gender, email, login_account from t_employee WHERE ( empname like ? and gender = ? )
            EmployeeExample.Criteria criteria = employeeExample.createCriteria();
            criteria.andEmpnameLike("%q%");
            criteria.andGenderEqualTo(1);

            //select id, empname, gender, email, login_account from t_employee WHERE ( empname like ? and gender = ? ) or( email like ? )
            EmployeeExample.Criteria criteria2 = employeeExample.createCriteria();
            criteria2.andEmailLike("%x%");
            employeeExample.or(criteria2);


            List<Employee> employees1 = mapper.selectByExample(employeeExample);



            for (Employee employee : employees1) {
                System.out.println(employee.getId());
            }
        } finally {
            openSession.close();
        }

    }

}
