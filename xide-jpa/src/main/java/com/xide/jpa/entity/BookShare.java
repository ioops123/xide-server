package com.xide.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 图书分享
 */
@Data
@Entity
@Table(name = "yw_book_share")
public class BookShare {
    @Id
    private String id;//ID
    @Column(name = "user_id")
    private String userId;//人员ID
    @Column(name = "book_id")
    private String bookId;//图书ID
    @Column(name = "share_type")
    private String shareType;//分享类型(haibao:海报、weixin:微信、weixin-circle:微信朋友圈、QQ:qq、weibo:微博)
    @Column(name = "create_time")
    private Date createTime;//创建时间
}
