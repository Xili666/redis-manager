package xyz.xili.redismanager.service;

import xyz.xili.redismanager.bean.Type;

import java.sql.SQLException;
import java.util.List;

public interface TypeService {

    Type getType(String key) throws SQLException;

    List<Type> getAllTypeList() throws SQLException;

    void saveType(Type type) throws SQLException;

    void deleteType(Integer id) throws SQLException;
}
