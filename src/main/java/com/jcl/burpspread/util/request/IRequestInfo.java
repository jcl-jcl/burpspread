package com.jcl.burpspread.util.request;
import com.jcl.burpspread.domain.DataBucketsDO;
import com.jcl.burpspread.vo.burpvo.HeardVO;
import com.jcl.burpspread.vo.burpvo.SaveRequestVO;

import java.io.IOException;
import java.nio.file.Path;

public interface IRequestInfo {
    HeardVO analyzeHeaders(SaveRequestVO saveRequestVO);

    String getCsvText(DataBucketsDO dataBucketsDO);

    String getPyText(DataBucketsDO dataBucketsDO, Path path) throws IOException;

}
