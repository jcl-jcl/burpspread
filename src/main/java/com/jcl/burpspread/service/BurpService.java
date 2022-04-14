package com.jcl.burpspread.service;

import com.jcl.burpspread.annotation.LogAnnotation;
import com.jcl.burpspread.constant.FilterConfigConstant;
import com.jcl.burpspread.domain.DataBucketsDO;
import com.jcl.burpspread.logic.BurpLogic;
import com.jcl.burpspread.logic.PyTestLogic;
import com.jcl.burpspread.vo.burpvo.SaveRequestVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("BurpService")
@Slf4j
@Api("Burp接口")
public class BurpService {
    private static boolean flag = true;
    @Autowired
    BurpLogic logic;

    @Autowired
    PyTestLogic pyTestLogic;

    @LogAnnotation
    @PostMapping("saveRequest")
    public void saveRequest(@RequestBody SaveRequestVO saveRequestVO){
        if (saveRequestVO.getHeaders().stream().anyMatch(x->
                FilterConfigConstant.JCL_FILTER.getFilters().stream().anyMatch(y->x.contains(y)))){
            DataBucketsDO dataBucketsDO = logic.analyzeRequest(saveRequestVO);
            log.debug("{}方法请求，请求内容{}", dataBucketsDO.getMethodname(),dataBucketsDO);
            logic.saveRequest(dataBucketsDO);

            if (flag&&FilterConfigConstant.JCL_FILTER.getUserId().equals(dataBucketsDO.getUserid())){
                try {
                    pyTestLogic.transToFile(dataBucketsDO);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
