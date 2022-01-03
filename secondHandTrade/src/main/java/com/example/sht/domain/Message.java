package com.example.sht.domain;

import lombok.Data;

import java.util.Date;

/**
 * 用户与用户通信记录
 */
@Data
public class Message {
    private long id;
    private User srcUser;
    private User dstUser;
    private Date createDate;
    private String content;
    private String pictureUrl;
    private int read;
}
