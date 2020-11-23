package com.sqx.dao;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;

import java.util.Properties;

/**
 * @author SQX
 * @date 2020/11/22 - 16:04
 */

@Intercepts({
        @Signature(type = StatementHandler.class, method = "parameterize", args = java.sql.Statement.class)
})
public class MySecondPlugin implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("MySecondPlugin...intercept:" + invocation.getMethod());
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object o) {
        System.out.println("MySecondPlugin...plugin:" + o);
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
