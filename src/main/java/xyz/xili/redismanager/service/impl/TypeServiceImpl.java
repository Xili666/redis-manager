package xyz.xili.redismanager.service.impl;

import org.springframework.data.redis.connection.DataType;
import org.springframework.stereotype.Service;
import xyz.xili.redismanager.bean.Type;
import xyz.xili.redismanager.dao.KeyDao;
import xyz.xili.redismanager.dao.TypeDao;
import xyz.xili.redismanager.service.AbstractRedisService;
import xyz.xili.redismanager.service.TypeService;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

@Service
public class TypeServiceImpl extends AbstractRedisService implements TypeService {

    @Resource
    private TypeDao typeDao;

    @Resource
    private KeyDao keyDao;


    @Override
    public Type getType(String key) throws SQLException {
        DataType type = keyDao.type(key);
        return typeDao.selectType(type, key);
    }

    @Override
    public List<Type> getAllTypeList() throws SQLException {
        return typeDao.getAllTypeList();
    }

    @Override
    public void saveType(Type type) throws SQLException {
        typeDao.insertType(type);
    }

    @Override
    public void deleteType(Integer id) throws SQLException {
        typeDao.deleteType(id);

    }
}
