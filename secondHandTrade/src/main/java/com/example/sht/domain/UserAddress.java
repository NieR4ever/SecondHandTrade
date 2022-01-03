package com.example.sht.domain;

import lombok.Data;

/**
 * 用户地址
 */
@Data
public class UserAddress {
    private long id;
    private long userId;
    private String address;
    private int defaultSign;
}
