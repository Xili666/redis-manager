package xyz.xili.redismanager.service;

import xyz.xili.redismanager.bean.Key;

import java.util.List;
import java.util.Map;

public interface HashService {
    List<Key> keys(String key);

    Map<String, String> list(String key);
}
