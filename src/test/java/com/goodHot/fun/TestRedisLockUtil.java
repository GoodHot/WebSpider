package com.goodHot.fun;

import com.goodHot.fun.aop.RedisDistributeLockAOP;
import com.goodHot.fun.conf.UpYunConfig;
import com.goodHot.fun.util.FileUtil;
import com.goodHot.fun.util.UpYunUtil;
import com.upyun.UpException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;


public class TestRedisLockUtil extends BaseTestsuit {

    @Autowired
    private RedisDistributeLockAOP redisDistributeLockAOP;

    @Test
    public void testRedisLock() throws IOException, UpException {
        redisDistributeLockAOP.getRedisLock("kkk");
    }
}
