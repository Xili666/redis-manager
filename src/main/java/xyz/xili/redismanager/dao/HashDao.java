package xyz.xili.redismanager.dao;

import java.util.Set;

public interface HashDao {

    String get(String key, Object hashKey);

    void set(String key, String hashKey, String value);

    Set<Object> keys(String key);
}
