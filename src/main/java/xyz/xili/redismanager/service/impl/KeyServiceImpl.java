package xyz.xili.redismanager.service.impl;

import org.springframework.data.redis.connection.DataType;
import org.springframework.stereotype.Service;
import xyz.xili.redismanager.bean.Key;
import xyz.xili.redismanager.bean.PageBean;
import xyz.xili.redismanager.dao.KeyDao;
import xyz.xili.redismanager.holder.KeysHolder;
import xyz.xili.redismanager.service.KeyService;
import xyz.xili.redismanager.service.AbstractRedisService;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;

@Service
public class KeyServiceImpl extends AbstractRedisService implements KeyService {

    @Resource
    private KeyDao keyDao;

    private KeysHolder keysHolder;

    @PostConstruct
    public void init() {
        this.keysHolder = new KeysHolder();
        keysHolder.setKeys(this.keys());
    }

    @Override
    public DataType type(String key) {
        return keyDao.type(key);
    }

    @Override
    public List<Key> keys(String pattern) {
        Set<String> keys = keyDao.keys(pattern);
        List<Key> list = new ArrayList<>();
        TreeSet<String> set = new TreeSet<>(keys);
        for (String s : set) {
            list.add(new Key(s));
        }
        return list;
    }

    @Override
    public List<Key> keys() {
        return this.keys("*");
    }

    @Override
    public List<Key> keysFormCache(String pattern) {
        List<Key> keys = this.keysHolder.getKeys();
        if (pattern.isEmpty()) {
            return keys;
        } else {
            List<Key> list = new ArrayList<>();
            for (Key key : keys) {
                if (match(key.getText(), pattern)) {
                    list.add(key);
                }
            }
            return list;
        }
    }

    @Override
    public List<Key> keysFormCache() {
        return this.keysFormCache("*");
    }

    @Override
    public void refreshKeysCache() {
        this.keysHolder.setKeys(keys());
    }

    @Override
    public PageBean<Key> pageQueryKeysFromCache(String pattern, final boolean asc, int pageNumber, int pageSize) {
        if (pageNumber < 1 || pageSize <= 0) {
            throw new IllegalArgumentException(String.format("请求参数不合法!%d|%d", pageNumber, pageSize));
        }
        PageBean<Key> pageBean = new PageBean<>(pageSize * (pageNumber - 1), pageSize);
        List<Key> keys = keysFormCache(pattern);
        Collections.sort(keys, new Comparator<Key>() {
            @Override
            public int compare(Key o1, Key o2) {
                if (asc) {
                    return o1.getText().compareTo(o2.getText());
                } else {
                    return o2.getText().compareTo(o1.getText());
                }
            }
        });
        pageBean.setResult(keys.subList(pageBean.getOffset(),
                pageBean.getOffset() + (keys.size() - pageBean.getOffset() > pageBean.getLimit() ? pageBean.getLimit() : keys.size() - pageBean.getOffset())));
        pageBean.setTotal(keys.size());
        return pageBean;
    }

    private boolean match(String text, String pattern) {
        if (pattern.length() == 0) {
            return false;
        }
        StringBuilder p = new StringBuilder();
        char[] chars = pattern.toCharArray();
        if (chars[0] == '*') {
            p.append("(.*)");
        } else if (chars[0] == '?') {
            p.append("(.)");
        } else {
            p.append(chars[0]);
        }
        if (chars.length > 1) {
            for (int i = 1; i < chars.length; i++) {
                if (chars[i] == '*' && chars[i - 1] != '\\') {
                    p.append("(.*)");
                } else if (chars[i] == '?' && chars[i - 1] != '\\') {
                    p.append("(.)");
                } else {
                    p.append(chars[i]);
                }
            }
        }
        return text.matches(p.toString());
    }

}
