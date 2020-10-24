package xyz.xili.redismanager.service.impl;

import org.springframework.stereotype.Service;
import xyz.xili.redismanager.dao.ByteDao;
import xyz.xili.redismanager.dao.StringDao;
import xyz.xili.redismanager.service.AbstractRedisService;
import xyz.xili.redismanager.service.StringService;
import xyz.xili.redismanager.util.SerializationUtil;

import javax.annotation.Resource;

@Service
public class StringServiceImpl extends AbstractRedisService implements StringService {

    @Resource
    private StringDao stringDao;
    @Resource
    private ByteDao byteDao;

    @Override
    public String get(String key) {
        return stringDao.get(key);
    }

    @Override
    public Object getSerialization(String key) {
        String s = stringDao.get(key);
        boolean serialized = false;
        for (char c : s.toCharArray()) {
            if (0xfffd == ((int) c)) {
                serialized = true;
                break;
            }
        }
        if (serialized) {
           return SerializationUtil.deserialize(byteDao.get(key));
        }
        return null;
    }

}
