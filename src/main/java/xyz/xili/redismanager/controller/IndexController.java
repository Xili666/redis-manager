package xyz.xili.redismanager.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.xili.redismanager.bean.Index;
import xyz.xili.redismanager.service.IndexService;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/index")
public class IndexController extends AbstractRedisController {

    @Resource
    private IndexService indexService;

    @RequestMapping("/list")
    public Map<String, Object> index() {
        List<Index> indices = null;
        Map<String, Object> resultMap = getResultMap();
        try {
            indices = indexService.getAllIndexList();
        } catch (SQLException e) {
            processingError(resultMap, e);
        }
        if (indices != null) {
            setResult(resultMap, indices);
        }
        return resultMap;
    }

    @PostMapping("/add")
    public Map<String, Object> add(String text, String pattern){
        Index index = new Index();
        index.setText(text);
        index.setPattern(pattern);
        Map<String, Object> resultMap = getResultMap();
        try {
            indexService.saveIndex(index);
        } catch (SQLException e) {
            processingError(resultMap, e);
        }
        return resultMap;
    }
}
