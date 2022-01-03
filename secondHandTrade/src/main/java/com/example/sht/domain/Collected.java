package com.example.sht.domain;

import lombok.Data;

import java.util.Date;

/**
 * 用户收藏
 */
@Data
public class Collected {
    private long id;
    private User user;
    private Post post;
    private Date createDate;
}
