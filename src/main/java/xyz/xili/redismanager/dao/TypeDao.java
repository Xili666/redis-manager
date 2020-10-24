package xyz.xili.redismanager.dao;

import org.springframework.data.redis.connection.DataType;
import xyz.xili.redismanager.bean.Type;

import java.sql.SQLException;
import java.util.List;

public interface TypeDao {
    List<Type> getAllTypeList() throws SQLException;

    void insertType(Type type) throws SQLException;

    void deleteType(Integer id) throws SQLException;

    Type selectType(DataType redisType, String pattern) throws SQLException;
}
