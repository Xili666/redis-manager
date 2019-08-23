package xyz.xili.redismanager.service.impl;

import org.springframework.stereotype.Service;
import xyz.xili.redismanager.dao.StringDao;
import xyz.xili.redismanager.service.AbstractRedisService;
import xyz.xili.redismanager.service.StringService;

import javax.annotation.Resource;

@Service
public class StringServiceImpl extends AbstractRedisService implements StringService {

    @Resource
    private StringDao stringDao;

    @Override
    public String get(String key) {
        return stringDao.get(key);
    }

}
