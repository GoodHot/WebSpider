package com.goodHot.fun.conf;

import com.UpYun;
import com.goodHot.fun.util.Download;
import com.goodHot.fun.util.VedioUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.client.RestTemplate;

import java.io.FileNotFoundException;

@Configuration
public class BeanConfig {


    @Autowired
    private UpYunConfig upYunConfig;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public Download download() {
        return new Download();
    }

    @Bean
    public RetryTemplate retryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();

        FixedBackOffPolicy fixedBackOffPolicy = new FixedBackOffPolicy();
        fixedBackOffPolicy.setBackOffPeriod(2000l);
        retryTemplate.setBackOffPolicy(fixedBackOffPolicy);

        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
        retryPolicy.setMaxAttempts(4);
        retryTemplate.setRetryPolicy(retryPolicy);

        return retryTemplate;
    }

    @Bean
    public UpYun upYun() {
        return new UpYun(upYunConfig.getBucket().getName(),
                upYunConfig.getOperator().getName(),
                upYunConfig.getOperator().getPwd()) {{
            setTimeout(60);
        }};
    }

    @Bean
    public VedioUtil vedioUtil() {
        return new VedioUtil();
    }

    @Bean
    public DefaultRedisScript defaultRedisScript() throws FileNotFoundException {
        DefaultRedisScript defaultRedisScript = new DefaultRedisScript();
        defaultRedisScript.setLocation(new ClassPathResource("redisDistributeLock.lua"));
        defaultRedisScript.setResultType(Boolean.class);
        return defaultRedisScript;
    }
}
