package xyz.xili.redismanager.bean;

import lombok.Data;

import java.util.List;

@Data
public class PageBean<T> {

    // 偏移量
    private int offset;

    // 条数
    private int limit;

    // 总数
    private int total;

    // 结果集
    private List<T> result;

    public PageBean(int offset, int limit) {
        this.offset = offset;
        this.limit = limit;
    }

    /**
     * 返回当前页条数
     */
    public int getPageSize() {
        return limit;
    }

    /**
     * 返回当前的页面数
     */
    public int getPageNumber() {
        return offset / limit + 1;
    }

    /**
     * 返回总页面数
     */
    public int getTotalPageNumber() {
        return total / limit + (total % limit > 0 ? 1 : 0);
    }
}
