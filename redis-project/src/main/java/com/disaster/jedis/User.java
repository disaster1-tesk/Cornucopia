package com.disaster.jedis;


import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@Builder
@ToString
public class User implements Serializable {
    private String name;
    private Integer age;
}
