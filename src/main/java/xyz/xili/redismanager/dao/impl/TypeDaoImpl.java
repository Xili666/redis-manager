package xyz.xili.redismanager.dao.impl;

import org.springframework.data.redis.connection.DataType;
import org.springframework.stereotype.Repository;
import xyz.xili.redismanager.bean.Type;
import xyz.xili.redismanager.dao.AbstractSqliteDao;
import xyz.xili.redismanager.dao.TypeDao;
import xyz.xili.redismanager.util.DataTypeUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TypeDaoImpl extends AbstractSqliteDao implements TypeDao {

    @Override
    public List<Type> getAllTypeList() throws SQLException {
        List<Type> typeList = new ArrayList<>();
        String sql = "select id, redisType, javaType, pattern from t_type";
        try (Statement statement = sqliteConnection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String redisType = resultSet.getString("redisType");
                    String javaType = resultSet.getString("javaType");
                    String pattern = resultSet.getString("pattern");
                    Type type = new Type(id, redisType, javaType, pattern);
                    typeList.add(type);
                }
            }
        }
        return typeList;
    }

    @Override
    public void insertType(Type type) throws SQLException {
        String sql = "insert into t_type (redisType, javaType, pattern) values (?, ?, ?)";
        try (PreparedStatement statement = sqliteConnection.prepareStatement(sql)) {
            statement.setString(1, type.getRedisType());
            statement.setString(2, type.getJavaType());
            statement.setString(3, type.getPattern());
            statement.execute();
            int id;
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                id = generatedKeys.getInt(1);
            }
            type.setId(id);
            sqliteConnection.commit();
        }
    }

    @Override
    public void deleteType(Integer id) throws SQLException {
        String sql = "delete from t_type where id = ?";
        try (PreparedStatement statement = sqliteConnection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.execute();
            sqliteConnection.commit();
        }
    }

    @Override
    public Type selectType(DataType redisType, String pattern) throws SQLException {
        Type type = null;
        String redisTypeStr = DataTypeUtil.DataTypetoString(redisType);
        String sql = "select id, redisType, javaType, pattern from t_type where redisType = ? and pattern = ?";
        try (PreparedStatement statement = sqliteConnection.prepareStatement(sql)) {
            statement.setString(1, redisTypeStr);
            statement.setString(2, pattern);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String redisTypeRStr = resultSet.getString("redisType");
                    String javaTypeRStr = resultSet.getString("javaType");
                    String patternRStr = resultSet.getString("pattern");
                    type = new Type(id, redisTypeRStr, javaTypeRStr, patternRStr);
                }
            }
        }
        return type;
    }
}
