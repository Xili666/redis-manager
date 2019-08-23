package xyz.xili.redismanager.dao;


public interface StringDao {

    String get(String key);

    void set(String key, String value);
}
