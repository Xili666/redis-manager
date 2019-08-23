package xyz.xili.redismanager;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.sqlite.javax.SQLiteConnectionPoolDataSource;

import javax.annotation.PreDestroy;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@SpringBootApplication
public class RedisManagerApplication {

    private Connection sqliteConnection;

    public static void main(String[] args) {
        SpringApplication.run(RedisManagerApplication.class, args);
    }

    @Bean
    public Connection sqliteConnection(@Value("${sqlite.url}") String url) throws SQLException {
        SQLiteConnectionPoolDataSource dataSource = new SQLiteConnectionPoolDataSource();
        dataSource.setUrl(url);
        sqliteConnection = dataSource.getConnection();
        sqliteConnection.setAutoCommit(false);
        try (Statement statement = sqliteConnection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery("select count(1) as exist from sqlite_master where type = 'table' and name = 't_index'")) {
                int exist = resultSet.getInt("exist");
                if (exist < 1) {
                    statement.executeUpdate("create table t_index (id integer primary key, text text not null, pattern text not null)");
                    sqliteConnection.commit();
                }
            }
        }
        return sqliteConnection;
    }

    @PreDestroy
    public void sqliteConnectionClose() throws SQLException {
        sqliteConnection.close();
    }

}
