package xyz.xili.redismanager.dao;


import org.springframework.data.redis.connection.DataType;

import java.util.Set;

public interface KeyDao {
    Set<String> keys(String pattern);

    Set<String> scan(String pattern);

    DataType type(String key);
}
