package com.jcl.burpspread.logic;

import com.alibaba.fastjson.JSON;
import com.jcl.burpspread.constant.IRequestInfoConstant;
import com.jcl.burpspread.dao.DataBucketsDOMapper;
import com.jcl.burpspread.domain.DataBucketsDO;
import com.jcl.burpspread.util.request.DoctorApp;
import com.jcl.burpspread.util.request.IRequestInfo;
import com.jcl.burpspread.util.request.PatientApp;
import com.jcl.burpspread.vo.burpvo.HeardVO;
import com.jcl.burpspread.vo.burpvo.SaveRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.Map;

import static com.jcl.burpspread.constant.IRequestInfoConstant.PATIENT_ID;


@Component
public class BurpLogic {
    @Autowired
    private DataBucketsDOMapper dataBucketMapper;

    public int saveRequest(DataBucketsDO dataBucketsDO){
        DataBucketsDO dataBucketsDO1 = dataBucketMapper.selectRequest(dataBucketsDO.getUserid(),
                dataBucketsDO.getPatientid(), dataBucketsDO.getMethodname());
        int var=0;
        if(dataBucketsDO1 == null){
            var = dataBucketMapper.insert(dataBucketsDO);
        }else {
            dataBucketsDO.setId(dataBucketsDO1.getId());
            dataBucketsDO.setUtime(new Date());
            var = dataBucketMapper.updateByPrimaryKey(dataBucketsDO);
        }
        return var;
    }


    public DataBucketsDO analyzeRequest(SaveRequestVO saveRequestVO){
        DataBucketsDO dataBucketsDO = new DataBucketsDO();
        IRequestInfo request = getRequest(saveRequestVO);
        HeardVO heardVO = request.analyzeHeaders(saveRequestVO);
        dataBucketsDO.setUrl(heardVO.getUrl());
        dataBucketsDO.setPlatform(heardVO.getPlatform());
        dataBucketsDO.setUserid(heardVO.getUserId()==null?0L:heardVO.getUserId());
        dataBucketsDO.setMethodname(heardVO.getMethodName());
        dataBucketsDO.setCookies(heardVO.getCookies()==null?"":heardVO.getCookies());
        dataBucketsDO.setRequestmethod(saveRequestVO.getMethod());
        dataBucketsDO.setSystemname(heardVO.getSysName());
        Map<String, String> param = saveRequestVO.getParam();
        dataBucketsDO.setParams(JSON.toJSONString(param));
        if(param.containsKey(PATIENT_ID)){
            dataBucketsDO.setPatientid(Long.valueOf(param.get(PATIENT_ID)));
        }
        dataBucketsDO.setContenttype((int)saveRequestVO.getContentType());
        return dataBucketsDO;
    }

    private IRequestInfo getRequest(SaveRequestVO saveRequestVO){
        IRequestInfo request = null;
        if(saveRequestVO.getHeaders().stream().anyMatch(x->x.contains(IRequestInfoConstant.PATIENT_API))){
            request = new PatientApp();
        }else if(saveRequestVO.getHeaders().stream().anyMatch(x-> x.contains(IRequestInfoConstant.DOCTOR_API))){
            request = new DoctorApp();
        }
        return request;
    }
}
