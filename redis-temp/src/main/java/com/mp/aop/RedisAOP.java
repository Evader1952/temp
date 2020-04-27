package com.mp.aop;

import com.mp.util.CheckObjectIsNullUtils;
import com.mp.util.RedisKeyUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Set;
import java.util.concurrent.TimeUnit;


/**
 * @ClassName RedisAOP
 * @description: redis 切面缓存
 **/
@Aspect
@Service
public class RedisAOP {
    private static final Integer TIME_OUT = 120; //redis 存活时长 分钟
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * @return void
     * @Title: queryCachePointcut
     * @Description: 定义切点为缓存注解
     **/
    @Pointcut("@within(com.mp.annotation.RedisCache)")
    public void queryCachePointcut() {
    }

    @Around("queryCachePointcut()")
    public Object Interceptor(ProceedingJoinPoint joinPoint) throws Throwable {
        long beginTime = System.currentTimeMillis();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取参数
        Object[] args = joinPoint.getArgs();
        StringBuffer arg = new StringBuffer();
        if (args.length > 0) {
            for (Object a : args) {
                if (a != null)
                    CheckObjectIsNullUtils.objCheckIsNull(a, arg);
            }
        }
        //类路径名
        String classPathName = joinPoint.getTarget().getClass().getName();
        //类名
        String className = classPathName.substring(classPathName.lastIndexOf(".") + 1, classPathName.length());
        //获取方法名
        String methodName = signature.getMethod().getName();
        String[] strings = signature.getParameterNames();

        String key = RedisKeyUtil.getKey(className, methodName,"" + arg);
//        String key = className + "_" + methodName + "_" + Arrays.toString(strings) + "_" + arg;

        if ((methodName.indexOf("list") != -1) || (methodName.indexOf("find") != -1 || (methodName.indexOf("get") != -1))) {
            /*if (methodName.equals("list") && className.indexOf("Service") != -1) {
                try {
                    Object obj = getObject(beginTime, joinPoint, key);
                    List<PetPermission> list = JSONObject.parseArray(JSONObject.toJSONString(obj)).toJavaList(PetPermission.class);
                    return list;
                } catch (Exception e) {
                    return joinPoint.proceed();
                }

            } else if (methodName.equals("findMenu") && className.indexOf("Service") != -1) {
                try {
                    Object obj = getObject(beginTime, joinPoint, key);
                    PetPermission list = JSONObject.parseObject(JSONObject.toJSONString(obj), PetPermission.class);
                    return list;
                } catch (Exception e) {
                    return joinPoint.proceed();
                }
            } else {*/
            try {
                LinkedHashMap map = (LinkedHashMap) getObject(beginTime, joinPoint, key);

//                return Result.ok(map.get("data"));
            } catch (Exception e) {
                return joinPoint.proceed();
            }
            /* }*/


        } else if ((methodName.indexOf("add") != -1) || (methodName.indexOf("del") != -1) || (methodName.indexOf("update") != -1)) {
            Set<String> keys = redisTemplate.keys(className + "*");
            redisTemplate.delete(keys);
            System.err.println("执行方法 : [ " + methodName + " ] : 清除 key 包含 [ " + className + " ] 的缓存数据");
            System.err.println("AOP 缓存切面处理 >>>> end 耗时：" + (System.currentTimeMillis() - beginTime));
        }
        // 调用原始方法
        return joinPoint.proceed();
    }

    /**
     * @param beginTime : 切面开始时间
     * @param joinPoint : 切面对象
     * @param key       : 获取redis数据的key值
     * @return java.lang.Object
     * @Title: getObject
     * @Description: 使用key获取数据 不存在则查询添加
     **/
    private Object getObject(long beginTime, ProceedingJoinPoint joinPoint, String key) throws Throwable {
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        boolean hasKey = redisTemplate.hasKey(key);
        Object object = null;
        if (hasKey) {
            // 缓存中获取到数据，直接返回。
            object = operations.get(key);
            System.err.println("从缓存中获取到 key 为 [" + key + " ] : 的数据 >>>> " + object.toString());
            System.err.println("AOP 缓存切面处理 >>>> end 耗时：" + (System.currentTimeMillis() - beginTime));
            return object;
        }
        if (object == null) {
            // 缓存中没有数据，调用原始方法查询数据库
            object = joinPoint.proceed();
            operations.set(key, object, TIME_OUT, TimeUnit.MINUTES); // 设置超时时间30分钟
            System.err.println("向 Redis 添加 key 为 [" + key + " ] , 存活时长为 " + TIME_OUT + " min 的数据 >>>> " + object.toString());
            System.err.println("AOP 缓存切面处理 >>>> end 耗时：" + (System.currentTimeMillis() - beginTime));
        }
        return object;
    }

    @Autowired(required = false)
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        RedisSerializer stringSerializer = new StringRedisSerializer();//序列化为String
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);//序列化为Json
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        this.redisTemplate = redisTemplate;
    }

}
