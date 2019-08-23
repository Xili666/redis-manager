package xyz.xili.redismanager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractRedisController {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    Map<String, Object> getResultMap() {
        Map<String, Object> objMap = new HashMap<>();
        objMap.put("ok", true);
        return objMap;
    }

    void processingError(Map<String, Object> map, Exception e) {
        logger.error(e.getMessage(), e);
        map.put("ok", false);
        map.put("err", e.getMessage());
    }

    void setResult(Map<String, Object> resultMap, Object result) {
        resultMap.put("result", result);
    }
}
