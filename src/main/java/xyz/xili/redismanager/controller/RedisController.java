package xyz.xili.redismanager.controller;

import org.springframework.data.redis.connection.DataType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.xili.redismanager.service.HashService;
import xyz.xili.redismanager.service.KeyService;
import xyz.xili.redismanager.service.StringService;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/redis")
public class RedisController extends AbstractRedisController {

    @Resource
    private KeyService keyService;
    @Resource
    private HashService hashService;
    @Resource
    private StringService stringService;

    @RequestMapping("/list")
    public Map<String, Object> list(String key) {
        DataType type = keyService.type(key);
        Object result = null;
        switch (type) {
            case HASH:
                result = hashService.list(key);
                break;
            case STRING:
                result = stringService.get(key);
                break;
            case NONE:
            case SET:
            case LIST:
            case ZSET:
            default:
                break;
        }
        Map<String, Object> resultMap = getResultMap();
        resultMap.put("type", type.code());
        resultMap.put("result", result);
        return resultMap;
    }

}
