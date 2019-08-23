package xyz.xili.redismanager.dao.impl;

import org.springframework.stereotype.Repository;
import xyz.xili.redismanager.dao.AbstractRedisDao;
import xyz.xili.redismanager.dao.StringDao;


@Repository
public class StringDaoImpl extends AbstractRedisDao implements StringDao {

    @Override
    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

}
