package xyz.xili.redismanager.dao.impl;

import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.stereotype.Repository;
import xyz.xili.redismanager.dao.AbstractRedisDao;
import xyz.xili.redismanager.dao.ByteDao;

import java.nio.charset.StandardCharsets;

@Repository
public class ByteDaoImpl extends AbstractRedisDao implements ByteDao {
    @Override
    public byte[] get(String key) {
        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
        return connection.get(key.getBytes(StandardCharsets.UTF_8));
    }
}
