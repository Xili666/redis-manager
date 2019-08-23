package xyz.xili.redismanager.holder;

import xyz.xili.redismanager.bean.Key;

import java.util.List;

/**
 * 暂存读出来的keys
 */
public class KeysHolder {

    private List<Key> keys;

    public void setKeys(List<Key> keys) {
        synchronized (this) {
            this.keys = keys;
        }
    }

    public List<Key> getKeys() {
        synchronized (this) {
            return keys;
        }
    }
}
