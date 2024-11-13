package com.disaster.springbootredis.pojo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private String username;
    private String password;
    private Integer age;
    private Integer height;
    private Integer weight;
}
