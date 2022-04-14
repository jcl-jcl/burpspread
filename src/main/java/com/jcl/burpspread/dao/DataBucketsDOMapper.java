package com.jcl.burpspread.dao;

import com.jcl.burpspread.common.MySqlExtensionMapper;
import com.jcl.burpspread.domain.DataBucketsDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DataBucketsDOMapper extends MySqlExtensionMapper<DataBucketsDO> {
    DataBucketsDO selectRequest(@Param(value = "userid") Long userid, @Param(value ="patientid") Long patientid, @Param(value ="methodname") String methodname);

}