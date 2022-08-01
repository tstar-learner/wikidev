package com.jiawa.wikidev.req;

public class PageReq {
    private int start;
    private int size;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    @Override
    public String toString() {
        return "PageReq{" +
                "start=" + start +
                ", size=" + size +
                '}';
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
