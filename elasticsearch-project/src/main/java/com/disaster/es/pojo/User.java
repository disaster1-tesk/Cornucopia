package com.disaster.es.pojo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private Long id;
    private String name;
    private String type;
    private int age;
    private int height;
    private int weight;
}
