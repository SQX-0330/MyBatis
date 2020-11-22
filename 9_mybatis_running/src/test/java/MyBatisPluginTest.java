/**
 * @author SQX
 * @date 2020/11/22 - 14:58
 */
public class MyBatisPluginTest {


    /**
     * 插件原理
     * 在创建四大对象的时候
     * 1.每个创建出来的独享不是直接返回的，而是
     *      interceptorChain.pluginAll（parameterHandler）
     * 2.获取到所有的Interceptor（拦截器）（插件需要实现的接口）
     *              调用interceptor.plugin(target);返回terget包装后的对象。
     * 3.插件机制，我没抗压使用插件为目标创建一个代理对象：AOP（面向切面）
     *      我们的插件可为四大对象创建出代理对象
     *      代理对象就可以拦截到四大对象的每一个执行
     *
     */

    /**
     * 插件编写
     * 1.编写Interceptor的实现类
     * 2.编写@Intercepts注解完成插件签名
     * 3.将写好的插件注册到全局配置文件中
     */
}
