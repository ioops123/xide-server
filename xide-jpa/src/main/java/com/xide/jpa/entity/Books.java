package com.xide.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 图书信息
 */
@Data
@Entity
@Table(name = "yw_books")
public class Books {
    @Id
    private String id;//ID
    @Column(name = "title")
    private String title;//图书名称
    @Column(name = "author")
    private String author;//作者
    @Column(name = "images_medium")
    private String imagesMedium;//缩略图
    @Column(name = "images_large")
    private String imagesLarge;//大图
    @Column(name = "summary")
    private String summary;//内容简介
    @Column(name = "publisher")
    private String publisher;//出版社名称
    @Column(name = "pages")
    private int pages;//总页数
    @Column(name = "create_time")
    private Date createTime;//创建时间
    @Column(name = "update_time")
    private Date updateTime;//修改时间
}
