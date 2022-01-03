package com.example.sht.domain;

import lombok.Data;

import java.util.List;

/**
 * 用户
 */
@Data
public class User {
    private long id;
    private String name;
    private String password;
    private int phone;
    private String email;
    private String description;
    private String avatarUrl;
    private int gender;
    private long likeCount;
    private long collectedCount;
    private long followCount;
    private long followedCount;

    private List<UserAddress> userAddressList;

}
