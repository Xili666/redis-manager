package xyz.xili.redismanager.dao.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Repository;
import xyz.xili.redismanager.dao.AbstractRedisDao;
import xyz.xili.redismanager.dao.KeyDao;

import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

@Repository
public class KeyDaoImpl extends AbstractRedisDao implements KeyDao {

    /**
     * KEYS pattern
     * <p>
     * 查找所有符合给定模式 pattern 的 key 。
     * <p>
     * KEYS * 匹配数据库中所有 key 。
     * KEYS h?llo 匹配 hello ， hallo 和 hxllo 等。
     * KEYS h*llo 匹配 hllo 和 heeeeello 等。
     * KEYS h[ae]llo 匹配 hello 和 hallo ，但不匹配 hillo 。
     * 特殊符号用 \ 隔开
     *
     * @param pattern
     * @return
     */
    @Override
    public Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    @Override
    public Set<String> scan(String pattern) {
        return scan(pattern, 10);
    }

    @Override
    public DataType type(String key) {
        return redisTemplate.type(key);
    }

    private Set<String> scan(final String pattern, final long count) {
        return redisTemplate.execute(new RedisCallback<Set<String>>() {
            @Override
            public Set<String> doInRedis(RedisConnection connection) throws DataAccessException {
                ScanOptions scanOptions = ScanOptions.scanOptions().match(pattern).count(count).build();
                Set<String> keys = new TreeSet<>();
                try (Cursor<byte[]> cursor = connection.scan(scanOptions)) {
                    while (cursor.hasNext()) {
                        keys.add(new String(cursor.next()));
                    }
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
                return keys;
            }
        });
    }
}
