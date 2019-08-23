package xyz.xili.redismanager.dao.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.stereotype.Repository;
import xyz.xili.redismanager.dao.AbstractRedisDao;
import xyz.xili.redismanager.dao.CommandDao;


@Repository
public class CommandDaoImpl extends AbstractRedisDao implements CommandDao {

    @Override
    public String ping() {
        return redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection redisConnection) throws DataAccessException {
                return redisConnection.ping();
            }
        });
    }

}
