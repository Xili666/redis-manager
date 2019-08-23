package xyz.xili.redismanager.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.xili.redismanager.service.HashService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/hash")
public class HashController extends AbstractRedisController {

    @Resource
    private HashService hashService;

    @RequestMapping("/list")
    public Map<String, Object> list(String key) {
        Map<String, Object> resultMap = getResultMap();
        Map<String, String> map = new HashMap<>();
        try {
            map = hashService.list(key);
        } catch (Exception e) {
            processingError(resultMap, e);
        }
        setResult(resultMap, map);
        return resultMap;
    }


}
