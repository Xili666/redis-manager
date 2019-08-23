package xyz.xili.redismanager.service.impl;

import org.springframework.stereotype.Service;
import xyz.xili.redismanager.bean.Key;
import xyz.xili.redismanager.dao.HashDao;
import xyz.xili.redismanager.service.AbstractRedisService;
import xyz.xili.redismanager.service.HashService;

import javax.annotation.Resource;
import java.util.*;

@Service
public class HashServiceImpl extends AbstractRedisService implements HashService {

    @Resource
    private HashDao hashDao;

    @Override
    public List<Key> keys(String key) {
        Set<Object> keys = hashDao.keys(key);
        List<Key> list = new ArrayList<>();
        for (Object o : keys) {
            list.add(new Key(((String) o)));
        }
        return list;
    }

    @Override
    public Map<String, String> list(String key) {
        Map<String, String> objMap = new TreeMap<>();
        for (Object o : hashDao.keys(key)) {
            objMap.put((String) o, hashDao.get(key, o));
        }
        return objMap;
    }
}
