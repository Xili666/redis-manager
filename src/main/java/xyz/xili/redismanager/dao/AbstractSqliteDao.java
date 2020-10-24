package xyz.xili.redismanager.dao;

import javax.annotation.Resource;
import java.sql.Connection;

public abstract class AbstractSqliteDao {

    @Resource
    protected Connection sqliteConnection;

}
