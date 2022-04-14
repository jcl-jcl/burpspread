package com.jcl.burpspread.service;

import com.jcl.burpspread.annotation.LogAnnotation;
import com.jcl.burpspread.dao.DataBucketsDOMapper;
import com.jcl.burpspread.domain.DataBucketsDO;
import com.jcl.burpspread.logic.PyTestLogic;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
@RequestMapping("PyTestService")
@Slf4j
@Api("PyTest接口")
public class PyTestService {
    @Autowired
    PyTestLogic pyTestLogic;

    @Autowired
    DataBucketsDOMapper dataBucketsDOMapper;

    @LogAnnotation
    @PostMapping("transToFile")
    public void transToFile(@RequestParam Long id){
        DataBucketsDO dataBucketsDO = dataBucketsDOMapper.selectByPrimaryKey(id);
        try {
            pyTestLogic.transToFile(dataBucketsDO);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
