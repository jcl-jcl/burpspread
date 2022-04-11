package com.jcl.burpspread;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan({"com.jcl.burpspread.dao"})
public class BurpspreadApplication {
    public static void main(String[] args) {
        SpringApplication.run(BurpspreadApplication.class, args);
    }

}
