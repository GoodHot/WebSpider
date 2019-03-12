package com.goodHot.fun.aop;

import com.goodHot.fun.exception.ExceptionHelper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;

import java.util.Collections;

/**
 * redis分布式锁
 */
@Aspect
@Slf4j
public class RedisDistributeLockAOP {

    @Autowired
    private RedisScript<Boolean> defaultRedisScript;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 获取锁
     * @param redisKey
     * @return
     */
    private Boolean getRedisLock(String redisKey) {
        // lua 实现
        log.info("redis lock: " + redisKey);
        return (Boolean) redisTemplate.execute(defaultRedisScript, Collections.singletonList(redisKey), "");
    }

    /**
     * 释放锁
     * @param redisKey
     */
    private void releaseRedisLock(String redisKey) {
        redisTemplate.delete(redisKey);
    }

    @Around("@annotation(RedisDistributeLockAnno)")
    public Object getAndReleaseLock(ProceedingJoinPoint proceedingJoinPoint, RedisDistributeLockAnno redisDistributeLockAnno) {
        // 获取锁
        ExceptionHelper.param(false == getRedisLock(redisDistributeLockAnno.redisLockKey()), "获取锁失败");
        Object v = null;
        try {
            v = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            // 释放锁
            releaseRedisLock(redisDistributeLockAnno.redisLockKey());
            return v;
        }
    }
}
