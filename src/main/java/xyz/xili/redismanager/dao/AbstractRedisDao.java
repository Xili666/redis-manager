package xyz.xili.redismanager.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

public abstract class AbstractRedisDao {

    protected Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Resource(name = "stringRedisTemplate")
    protected StringRedisTemplate redisTemplate;
}
