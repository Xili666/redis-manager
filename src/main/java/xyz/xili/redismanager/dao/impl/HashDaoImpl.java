package xyz.xili.redismanager.dao.impl;

import org.springframework.stereotype.Repository;
import xyz.xili.redismanager.dao.AbstractRedisDao;
import xyz.xili.redismanager.dao.HashDao;

import java.util.Set;


@Repository
public class HashDaoImpl extends AbstractRedisDao implements HashDao {

    @Override
    public String get(String key, Object hashKey) {
        return (String) redisTemplate.opsForHash().get(key, hashKey);
    }

    @Override
    public void set(String key, String hashKey, String value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    @Override
    public Set<Object> keys(String key) {
        return redisTemplate.opsForHash().keys(key);
    }

}
