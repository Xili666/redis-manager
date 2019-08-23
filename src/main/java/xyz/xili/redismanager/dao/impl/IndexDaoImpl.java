package xyz.xili.redismanager.dao.impl;

import org.springframework.stereotype.Repository;
import xyz.xili.redismanager.bean.Index;
import xyz.xili.redismanager.dao.IndexDao;

import javax.annotation.Resource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class IndexDaoImpl implements IndexDao {

    @Resource
    private Connection sqliteConnection;

    @Override
    public void insertIndex(Index index) throws SQLException {
        String sql = "insert into t_index (text, pattern) values (?, ?)";
        try (PreparedStatement statement = sqliteConnection.prepareStatement(sql)) {
            statement.setString(1, index.getText());
            statement.setString(2, index.getPattern());
            statement.execute();
            int id;
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                id = generatedKeys.getInt(1);
            }
            index.setId(id);
            sqliteConnection.commit();
        }
    }

    @Override
    public List<Index> queryAllIndexList() throws SQLException {
        List<Index> indexList = new ArrayList<>();
        String sql = "select id, text, pattern from t_index";
        try (Statement statement = sqliteConnection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String text = resultSet.getString("text");
                    String pattern = resultSet.getString("pattern");
                    Index index = new Index(id, text, pattern);
                    indexList.add(index);
                }
            }
        }
        return indexList;
    }
}
