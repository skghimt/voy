package cn.wondervoy.base;

public class BaseDomain implements java.io.Serializable {
    private long id;

    private String state = "N";

    private long createDate = System.currentTimeMillis();

    private long updateDate = 0;

    public BaseDomain() { /* compiled code */ }

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(long updateDate) {
        this.updateDate = updateDate;
    }
}
