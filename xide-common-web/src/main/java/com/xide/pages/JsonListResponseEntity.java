package com.xide.pages;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.collect.Lists;

import java.util.List;

@JsonInclude
public class JsonListResponseEntity<T> {

    private int code;
    private String msg;
    private ListData<T> data;

    public JsonListResponseEntity() {
        this.code = 0;
    }

    private class ListData<T> {
        public Boolean more;
        public String pageNum;
        public String pageSize;
        public List<T> content;

        public ListData() {
            this.more = false;
            this.content = Lists.newArrayList();
        }

        public ListData(List<T> content) {
            this.more = false;
            this.content = content;
        }

        public ListData(List<T> content, String pageNum, String pageSize) {
            this.more = content != null && content.size() >= Integer.valueOf(pageSize);
            this.pageNum = pageNum;
            this.pageSize = pageSize;
            this.content = content;
        }
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ListData<T> getData() {
        return data;
    }

    public void setData(ListData<T> data) {
        this.data = data;
    }

    public void setContent(List<T> content) {
        this.data = new ListData<>(content);
    }

    public void setContent(List<T> content, String pageNum, String pageSize) {
        this.data = new ListData<>(content, pageNum, pageSize);
    }
}
