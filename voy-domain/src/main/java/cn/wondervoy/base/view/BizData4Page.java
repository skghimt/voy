package cn.wondervoy.base.view;

import java.util.List;
import java.util.Map;

public class BizData4Page <T> {
    /** 当前页 */
    private int page;
    /** 总页数 */
    private int total;
    /** 总记录数 */
    private int records;
    /** 具体数据 */
    private List<T> rows;
    /** 返回的用户自定义数据 例如总计行？ */
    private Map<List,Object> userdata;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getRecords() {
        return records;
    }

    public void setRecords(int records) {
        this.records = records;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public Map<List, Object> getUserdata() {
        return userdata;
    }

    public void setUserdata(Map<List, Object> userdata) {
        this.userdata = userdata;
    }
}
