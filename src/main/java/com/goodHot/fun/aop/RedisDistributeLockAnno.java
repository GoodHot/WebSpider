package com.goodHot.fun.aop;


import java.lang.annotation.*;

/**
 * redis分布式锁
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedisDistributeLockAnno {
    String redisLockKey();
}
