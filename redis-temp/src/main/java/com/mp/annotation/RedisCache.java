package com.mp.annotation;

import java.lang.annotation.*;
/**
 * @Description: redis缓存注解 编写在需要缓存的类上
 **/
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisCache {

}
