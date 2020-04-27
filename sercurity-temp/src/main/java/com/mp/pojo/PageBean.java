package com.mp.pojo;

public class PageBean {
    private  Integer page=1;

    private  Integer limit=5;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = (page-1)*limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }


}
