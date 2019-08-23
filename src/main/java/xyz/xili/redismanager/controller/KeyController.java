package xyz.xili.redismanager.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.xili.redismanager.bean.Key;
import xyz.xili.redismanager.bean.PageBean;
import xyz.xili.redismanager.service.KeyService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/key")
public class KeyController extends AbstractRedisController {

    @Resource
    private KeyService keyService;

    @RequestMapping("/keys")
    public Map<String, Object> keys(String pattern, Integer pageNumber, Integer pageSize) {
        Map<String, Object> map = getResultMap();
        PageBean<Key> pageBean;
        try {
            pageBean = keyService.pageQueryKeysFromCache(pattern, true, pageNumber, pageSize);
        } catch (Exception e) {
            processingError(map, e);
            return map;
        }
        map.put("pageSize", pageBean.getPageSize());
        map.put("pageNumber", pageBean.getPageNumber());
        map.put("total", pageBean.getTotal());
        map.put("totalPageNumber", pageBean.getTotalPageNumber());
        List<Key> result = pageBean.getResult();
        List<String> list = new ArrayList<>();
        for (Key key : result) {
            list.add(key.getText());
        }
        setResult(map, list);
        return map;
    }

    @RequestMapping("/refresh")
    public Map<String, Object> refresh() {
        Map<String, Object> map = getResultMap();
        try {
            keyService.refreshKeysCache();
        } catch (Exception e) {
            processingError(map, e);
            return map;
        }
        return map;
    }

}
