package com.jcl.burpspread.util.request;
import com.google.common.collect.Lists;
import com.jcl.burpspread.constant.IRequestInfoConstant;
import com.jcl.burpspread.vo.burpvo.HeardVO;
import com.jcl.burpspread.vo.burpvo.SaveRequestVO;
import org.apache.commons.collections4.CollectionUtils;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class DoctorApp extends App{
    private final String regex_doctor_url = "(/doctorapi/\\w+)(?)";
    private final String platform = IRequestInfoConstant.DOCTOR_API;
    private final List<String> no_csv_param = Lists.newArrayList("spaceId");

    @Override
    public HeardVO analyzeHeaders(SaveRequestVO saveRequestVO) {
        List<String> headers = saveRequestVO.getHeaders();
        HeardVO heardVO = analyze(saveRequestVO, platform, regex_doctor_url);
        return heardVO;
    }

    @Override
    public void remove(List<String> paramKeys){
        no_csv_param.forEach(x->paramKeys.remove(x));
    }

    @Override
    public void transform(Map<String, String> map) {
        Collection<String> union = CollectionUtils.retainAll(map.keySet(), no_csv_param);
        union.forEach(x->{map.put(x,"$userId");});
    }
}
