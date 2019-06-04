package com.xide.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 图书阅读记录
 */
@Data
@Entity
@Table(name = "yw_reading_record")
public class ReadingRecord {
    @Id
    private String id;//ID
    @Column(name = "user_id")
    private String userId;//人员ID
    @Column(name = "bookshelf_id")
    private String bookshelfId;//书架ID
    @Column(name = "book_id")
    private String bookId;//图书ID
    @Column(name = "book_pages")
    private int bookPages;//图书总页数
    @Column(name = "reading_pages")
    private int readingPages;//已阅读页数
    @Column(name = "reading_percent")
    private int readingPercent;//已阅读百分比(四舍五入、取整)
    @Column(name = "reading_over")
    private String readingOver;//阅读结束标志(0:未结束,1:已结束)
    @Column(name = "create_time")
    private Date createTime;//创建时间
    @Column(name = "update_time")
    private Date updateTime;//修改时间
}
