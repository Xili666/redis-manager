package xyz.xili.redismanager.service;

import xyz.xili.redismanager.bean.Index;

import java.sql.SQLException;
import java.util.List;

public interface IndexService {
    List<Index> getAllIndexList() throws SQLException;

    void saveIndex(Index index) throws SQLException;
}
