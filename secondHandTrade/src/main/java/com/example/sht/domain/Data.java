package com.example.sht.domain;

import lombok.AllArgsConstructor;

import java.util.Map;

@lombok.Data
public class Data {
    private Map<String,Object> data;
    private String Msg;
    private int errorCode=0;
}
