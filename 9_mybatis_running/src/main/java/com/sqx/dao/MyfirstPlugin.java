package com.sqx.dao;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.util.Properties;

/**
 * @author SQX
 * @date 2020/11/22 - 15:06
 */

/**
 * 完成插件签名
 *     告诉Mybatis当前插件用来拦截哪个对象的哪个方法
 */
@Intercepts({
        @Signature(type = StatementHandler.class, method = "parameterize", args = java.sql.Statement.class)
})
public class MyfirstPlugin implements Interceptor {

    /**
     * intercept:拦截
     *          拦截目标对象的目标方法的执行
     * @param invocation
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        System.out.println("MyFirstPlugin...intercept:" + invocation.getMethod());

        //动态改变一下sql运行的参数：以前1号员工，实际从数据库中查询三号员工
        Object target = invocation.getTarget();
        System.out.println("当前拦截到的对象：" + target);
        //拿到LStatementHandler====》ParameterHandler===>parameterObject
        //年代target的元数据
        MetaObject metaObject = SystemMetaObject.forObject(target);
        Object value = metaObject.getValue("parameterHandler.parameterObject");

        System.out.println("sql语句用的参数是：" + value);
        //修改完sql语句要用的参数
        metaObject.setValue("parameterHandler.parameterObject", 11);

        //执行目标方法
        Object proceed = invocation.proceed();

        //返回执行后的返回值
        return proceed;
    }

    /**
     * plugin：
     *         包装目标对象的：包装：为目标对象创建一个代理对象
     * @param o
     * @return
     */
    @Override
    public Object plugin(Object o) {

        System.out.println("MyFirstPlugin...plugin:mybatis 将要包装的对象" + o);
        //我没可以借助Plugin的wrap方法使用当前的interceptor包装我们的目标对象
        Object wrap = Plugin.wrap(o, this);
        //返回当前o创建的动态代理
        return wrap;
    }

    /**
     * setProperties:
     *      将插件注册时的property属性设置进来
     * @param properties
     */
    @Override
    public void setProperties(Properties properties) {
        System.out.println("插件配置信息：" + properties);

    }
}
