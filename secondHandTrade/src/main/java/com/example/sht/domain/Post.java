package com.example.sht.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 用户帖子
 */
@EqualsAndHashCode
@Data
public class Post {
    private long id;
    private User user;
    @JsonFormat(pattern = "yyyy年MM月dd日HH时mm分ss秒")
    private Date createDate;
    private String title;
    private String content;
    private long scanCount;
    private int sold;
    private String coverPictureUrl;
    private double price;
    private Category category;
    private Quality quality;
    private int postCategory;
    private String keywords;
    private int amount;
    private String name;
}
