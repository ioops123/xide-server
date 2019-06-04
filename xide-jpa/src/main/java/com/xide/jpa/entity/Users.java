package com.xide.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 注册用户信息
 */
@Data
@Entity
@Table(name = "bs_users")
public class Users {
    @Id
    private String id;//ID
    @Column(name = "nickname")
    private String nickname;//昵称
    @Column(name = "gender")
    private String gender;//性别
    @Column(name = "birthday")
    private Date birthday;//出生年月
    @Column(name = "avatar")
    private String avatar;//头像地址
    @Column(name = "openid")
    private String openid;//微信openid
    @Column(name = "create_time")
    private Date createTime;//创建时间
    @Column(name = "update_time")
    private Date updateTime;//修改时间
}
