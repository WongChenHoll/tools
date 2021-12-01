package com.jason.test.common.bean;

/**
 * @author ChenHol.Wong
 * @create 2020/8/3 17:07
 */
public class PageQuery {

    private int start = 0; // 开始
    private int end = 20; // 结束
    private int pageSize = 20; // 每页多少数据，默认20
    private int pageNum = 1; // 第几页，默认第一页
    private int pagrNumTotal; // 总共多少页
    private int tatol; // 总数
    private boolean queryAll; // 是否查询全部

    public int getPagrNumTotal() {
        return pagrNumTotal;
    }

    public void setPagrNumTotal(int pagrNumTotal) {
        this.pagrNumTotal = pagrNumTotal;
    }

    public boolean getQueryAll() {
        return queryAll;
    }

    public void setQueryAll(boolean queryAll) {
        this.queryAll = queryAll;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getTatol() {
        return tatol;
    }

    public void setTatol(int tatol) {
        this.tatol = tatol;
    }
}
