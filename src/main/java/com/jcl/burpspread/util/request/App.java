package com.jcl.burpspread.util.request;

import com.jcl.burpspread.domain.DataBucketsDO;
import com.jcl.burpspread.util.FileUtils;
import com.jcl.burpspread.vo.burpvo.HeardVO;
import com.jcl.burpspread.vo.burpvo.SaveRequestVO;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class App implements IRequestInfo {
    protected final String regex_methodName = "(\\_)(\\w+)(\\?)";
    protected final String regex_userId = "(userinfo\\[userId\\]=)(\\w+)(\\;)";
    protected final String regex_cookies = "(Cookie: )(.+)";
    protected final String cookie = "Cookie";
    protected final String regex_sysName = "(/\\w+)(\\_)";
    protected final String[] noNeed = {"cid", "ck" ,"app", "deviceType", "hdfts", "certificateToken", "sv", "os", "di", "m", "n", "_t", "deviceToken", "p", "s", "v", "api", "hdfhs", "_hdfSignature"};
    protected final String no_text = "                        ";

    public HeardVO analyzeHeaders(SaveRequestVO saveRequestVO) {
        return null;
    }

    public String getCsvText(DataBucketsDO dataBucketsDO){
        String params = dataBucketsDO.getParams();
        Map<String, String> map_param = FileUtils.removeNONeed(params, noNeed);
        List<String> paramKeys = getCsvParam(map_param);
        Map<String, String> map_csv_1 = new HashMap<>();
        Map<String, String> map_csv = new HashMap<>();
        if (paramKeys.size()>0){
            map_csv = paramKeys.stream().map(s -> {
                map_csv_1.put(s, map_param.get(s).trim().length()>0?map_param.get(s):"");
                return map_csv_1;
            }).collect(Collectors.toList()).get(0);
        }
        String csvString = FileUtils.getCsvString(map_csv);
        return csvString;
    }

    public abstract void remove(List<String> paramKeys);

    public abstract void transform(Map<String, String> map);

    public String getPyText(DataBucketsDO dataBucketsDO, Path path) throws IOException {
        String py_template = FileUtils.getStingByFile(path);
        Map<String, String> remove = FileUtils.removeNONeed(dataBucketsDO.getParams(), noNeed);
        List<String> csvParam = getCsvParam(remove);
        StringBuilder csv_param_Builder = new StringBuilder();
        csvParam.forEach(x->{csv_param_Builder.append(x).append("-");});
        String csv_param = csv_param_Builder.substring(0,csv_param_Builder.length()-1);
        csvParam.forEach(x->{remove.put(x,"$"+x);});
        transform(remove);
        StringBuilder param_Builder = new StringBuilder();
        remove.keySet().forEach(x->{
            param_Builder.append("\"").append(x).append("\"").append(": ").append("\'").
                    append(remove.get(x)).append("\'").append(",").append("\n").append(no_text);
        });
        int i = param_Builder.lastIndexOf(",");
        StringBuilder param_String = param_Builder.delete(i, param_Builder.length());
        Map<String, Object> param = new HashMap<>();
        param.put("csv","\""+csv_param+"\""+" : \"${parameterize(data/doctor/"+dataBucketsDO.getMethodname()+".csv)}\"");
        param.put("testName", dataBucketsDO.getMethodname());
        param.put("param", param_String.toString());
        param.put("url", "http://" + dataBucketsDO.getUrl());
        param.put("token", "\"cid\": \"$userId\",\n" +
                "                        \"ct\": \"${generate_ct($userId)}\"");
        VelocityContext velocityContext = new VelocityContext(param);
        StringWriter writer = new StringWriter();
        boolean evaluate = Velocity.evaluate(velocityContext, writer, "logTag", py_template);
        String py_text = writer.getBuffer().toString();
        return py_text;
    }



    public String getPyText(DataBucketsDO dataBucketsDO) throws IOException {
        Path path = Paths.get("");
        return this.getPyText(dataBucketsDO, path);
    }


    public HeardVO analyze(SaveRequestVO saveRequestVO, String platform, String regex_url) {
        List<String> headers = saveRequestVO.getHeaders();
        HeardVO heardVO = new HeardVO();
        headers.forEach(x->{
            if (x.contains(platform)){
                String sysName1 = FileUtils.regex(x, regex_sysName).size()>0?FileUtils.regex(x, regex_sysName).get(1):"";
                String sysName = sysName1.substring(1);
                heardVO.setSysName(sysName);
                String methodName = FileUtils.regex(x, regex_methodName).size()>0?FileUtils.regex(x, regex_methodName).get(2):"";
                heardVO.setMethodName(methodName);
                if(x.contains(platform)){
                    String url = FileUtils.regex(x, regex_url).size()>0?FileUtils.regex(x, regex_url).get(1):"";
                    url = saveRequestVO.getHost()+url;
                    heardVO.setUrl(url);
                    heardVO.setPlatform(platform);
                }
            }
            if (x.contains(cookie)){
                String userId = FileUtils.regex(x, regex_userId).size()>0?FileUtils.regex(x, regex_userId).get(2):"0";
                heardVO.setUserId(Long.valueOf(userId));
            }
            if (x.contains(cookie)){
                String cookies =FileUtils.regex(x, regex_cookies).size()>0?FileUtils.regex(x, regex_cookies).get(2):"";
                heardVO.setCookies(cookies);
            }
        });
        return heardVO;
    }


    private List<String> getCsvParam(Map<String, String> map_param){
        List<String> paramKeys = new ArrayList<>();
        System.out.println(map_param);
        map_param.keySet().forEach(x->{
            if(map_param.get(x).length()<20){
                paramKeys.add(x);
            }
        });
        remove(paramKeys);
        return paramKeys;
    }

}
