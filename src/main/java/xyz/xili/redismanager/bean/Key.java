package xyz.xili.redismanager.bean;

import lombok.Data;

@Data
public class Key {
    private String text;

    public Key(String text) {
        this.text = text;
    }
}
