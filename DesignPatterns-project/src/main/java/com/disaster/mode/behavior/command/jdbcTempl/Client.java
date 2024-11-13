package com.disaster.mode.behavior.command.jdbcTempl;

import org.springframework.jdbc.core.JdbcTemplate;

public class Client {
    public static void main(String[] args) {
        //query方法使用了命令模式
        JdbcTemplate template = new JdbcTemplate();
    }
}
