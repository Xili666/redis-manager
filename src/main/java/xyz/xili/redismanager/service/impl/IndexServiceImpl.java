package xyz.xili.redismanager.service.impl;

import org.springframework.stereotype.Service;
import xyz.xili.redismanager.bean.Index;
import xyz.xili.redismanager.dao.IndexDao;
import xyz.xili.redismanager.service.IndexService;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

@Service
public class IndexServiceImpl implements IndexService {

    @Resource
    private IndexDao indexDao;

    @Override
    public List<Index> getAllIndexList() throws SQLException {
        return indexDao.queryAllIndexList();
    }

    @Override
    public void saveIndex(Index index) throws SQLException {
        indexDao.insertIndex(index);
    }

}
