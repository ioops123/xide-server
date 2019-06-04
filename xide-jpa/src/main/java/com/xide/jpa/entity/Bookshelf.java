package com.xide.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 书架
 */
@Data
@Entity
@Table(name = "yw_bookshelf")
public class Bookshelf {
    @Id
    private String id;//ID
    @Column(name = "user_id")
    private String userId;//人员ID
    @Column(name = "book_id")
    private String bookId;//图书ID
    @Column(name = "state")
    private String state;//阅读状态(0:未读、想读,1:在读,2:已读完)
    @Column(name = "create_time")
    private Date createTime;//创建时间
    @Column(name = "update_time")
    private Date updateTime;//修改时间
}
