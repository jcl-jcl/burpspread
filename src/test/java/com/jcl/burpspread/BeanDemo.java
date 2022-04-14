package com.jcl.burpspread;


import com.jcl.burpspread.config.BurpSaveConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class BeanDemo {
    @Autowired
    List<String> saveBurp;

    @Autowired
    BurpSaveConfig BurpSaveConfig;

    @Test
    public void test(){
        System.out.println(saveBurp);
        System.out.println(BurpSaveConfig.saveBurp());
    }
}
