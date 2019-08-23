package xyz.xili.redismanager.dao.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.xili.redismanager.bean.Index;
import xyz.xili.redismanager.dao.IndexDao;

import javax.annotation.Resource;

import java.sql.SQLException;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class IndexDaoImplTest {

    @Resource
    private IndexDao indexDao;

    @Test
    public void insertIndex() throws SQLException {
        Index index = new Index();
        index.setText("开户行");
        index.setPattern("*banllocation*");
        indexDao.insertIndex(index);
        System.out.println(index);
    }
}