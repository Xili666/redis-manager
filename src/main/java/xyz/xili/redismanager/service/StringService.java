package xyz.xili.redismanager.service;

public interface StringService {
    String get(String key);

    Object getSerialization(String key);
}
