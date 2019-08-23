package xyz.xili.redismanager.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.xili.redismanager.service.StringService;

import javax.annotation.Resource;

@RestController
@RequestMapping("/string")
public class StringController extends AbstractRedisController {
    @Resource
    private StringService stringService;

}
