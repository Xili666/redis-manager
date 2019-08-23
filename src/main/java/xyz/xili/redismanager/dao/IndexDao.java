package xyz.xili.redismanager.dao;

import xyz.xili.redismanager.bean.Index;

import java.sql.SQLException;
import java.util.List;

public interface IndexDao {
    void insertIndex(Index index) throws SQLException;

    List<Index> queryAllIndexList() throws SQLException;
}
