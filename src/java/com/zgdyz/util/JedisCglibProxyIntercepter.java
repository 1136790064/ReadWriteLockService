package com.zgdyz.util;


import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * 版   权: zysc.com
 * 包   名: com.zgdyz.util
 * 描   述: TODO
 * 创建时间: 2023/8/11 14:34
 *
 * @author: lijun
 */
public class JedisCglibProxyIntercepter implements MethodInterceptor {


    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        //try后会自动调用jedis的close方法释放资源
        try (Jedis jedis = RedisConnectPoll.getJedis()) {
            return method.invoke(jedis, objects);
        }
    }
}
