package com.dilen.gmall.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * change dev_wu
 */
@SpringBootApplication
@MapperScan(basePackages = "com.dilen.gmall.member.mapper")
public class GmallMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(GmallMemberApplication.class, args);
    }

}
