package xyz.xili.redismanager.util;

import org.springframework.data.redis.connection.DataType;

public class DataTypeUtil {
    public static String DataTypetoString(DataType dataType) {
        switch (dataType) {
            case SET:
                return "set";
            case HASH:
                return "hash";
            case LIST:
                return "list";
            case NONE:
                return "none";
            case ZSET:
                return "zset";
            case STRING:
                return "string";
        }
        throw new IllegalStateException("未知的类型");
    }
}
