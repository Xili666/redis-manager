package xyz.xili.redismanager.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.xili.redismanager.bean.Type;
import xyz.xili.redismanager.service.TypeService;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/type")
public class TypeController extends AbstractRedisController {

    @Resource
    private TypeService typeService;

    @RequestMapping("/list")
    public Map<String, Object> index() {
        List<Type> types = null;
        Map<String, Object> resultMap = getResultMap();
        try {
            types = typeService.getAllTypeList();
        } catch (SQLException e) {
            processingError(resultMap, e);
        }
        if (types != null) {
            setResult(resultMap, types);
        }
        return resultMap;
    }


    @PostMapping("/add")
    public Map<String, Object> add(String redisType, String javaType, String pattern) {
        Type type = new Type();
        type.setRedisType(redisType);
        type.setJavaType(javaType);
        type.setPattern(pattern);
        Map<String, Object> resultMap = getResultMap();
        try {
            typeService.saveType(type);
        } catch (SQLException e) {
            processingError(resultMap, e);
        }
        return resultMap;
    }

    @PostMapping("/del")
    public Map<String, Object> del(Integer id) {
        Map<String, Object> resultMap = getResultMap();
        try {
            typeService.deleteType(id);
        } catch (SQLException e) {
            processingError(resultMap, e);
        }
        return resultMap;
    }
}
