package xyz.xili.redismanager.service;

import org.springframework.data.redis.connection.DataType;
import xyz.xili.redismanager.bean.Key;
import xyz.xili.redismanager.bean.PageBean;

import java.util.List;

public interface KeyService {

    DataType type(String key);

    List<Key> keys(String pattern);

    List<Key> keys();

    List<Key> keysFormCache(String pattern);

    List<Key> keysFormCache();

    void refreshKeysCache();

    PageBean<Key> pageQueryKeysFromCache(String pattern, boolean asc, int pageNumber, int pageSize);
}
