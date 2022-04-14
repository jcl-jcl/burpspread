package com.jcl.burpspread.logic;
import com.jcl.burpspread.constant.IRequestInfoConstant;
import com.jcl.burpspread.domain.DataBucketsDO;
import com.jcl.burpspread.util.FileUtils;
import com.jcl.burpspread.util.request.DoctorApp;
import com.jcl.burpspread.util.request.IRequestInfo;
import com.jcl.burpspread.util.request.PatientApp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Paths;
import static com.jcl.burpspread.constant.FileConstant.csv_path;
import static com.jcl.burpspread.constant.FileConstant.test_path;

@Slf4j
@Component
public class PyTestLogic {
    public void transToFile(DataBucketsDO dataBucketsDO) throws IOException {
        IRequestInfo request = getRequest(dataBucketsDO);
        String csvText = request.getCsvText(dataBucketsDO);
        String path = this.getClass().getClassLoader().getResource("pattern.py").getPath();
        String pyText = request.getPyText(dataBucketsDO,Paths.get(path));
        log.debug("{}生成py和csv文件", dataBucketsDO.getMethodname());
        FileUtils.createFileByString(csvText, csv_path, dataBucketsDO.getMethodname()+".csv");
        FileUtils.createFileByString(pyText, test_path, dataBucketsDO.getMethodname()+"_test.py");
    }

    private IRequestInfo getRequest(DataBucketsDO dataBucketsDO){
        IRequestInfo request = null;
        if(dataBucketsDO.getUrl().contains(IRequestInfoConstant.PATIENT_API)){
            request = new PatientApp();
        }else if(dataBucketsDO.getUrl().contains(IRequestInfoConstant.DOCTOR_API)){
            request = new DoctorApp();
        }
        return request;
    }


}
