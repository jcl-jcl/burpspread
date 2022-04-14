package com.jcl.burpspread.util;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Slf4j
public class FileUtils {

    public static String getStingByFile(Path path) throws IOException {
        byte[] bytes = Files.readAllBytes(path);
        return new String(bytes);
    }

    /**
     *
     * @param text 文本内容
     * @param path 目录路径
     * @param fileName 文件名
     * @throws IOException
     */
    public static void createFileByString(String text, String path ,String fileName) throws IOException {
        File pathDic = new File(path);
        if (!pathDic.exists()){
            pathDic.mkdirs();
        }
        File outFile = new File(path + fileName);
        if (!outFile.exists()) {
            outFile.createNewFile();
        }
        Path outPath = Paths.get(outFile.getAbsolutePath());
        try (BufferedWriter out = Files.newBufferedWriter(outPath)) {
            out.write(text);
        }
    }

    /**
     *
     * @param content 源数据
     * @param regex 正则表达式
     * @return 可能为空数组
     */
    public static List<String> regex(String content, String regex){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        List<String> matcherContent = new ArrayList<>();
        if(matcher.find()){
            int i = matcher.groupCount()+1;
            for (int j = 0; j < i; j++) {
                matcherContent.add(matcher.group(j));
            }
        } else {
            log.warn("\"{}\"通过正则\"{}\"没有匹配到内容",content,regex);
        }
        return matcherContent;
    }


    /**
     * 过滤掉不需要的参数
     * @param json json字符串
     * @return
     */
    public static Map<String, String> removeNONeed(String json, String[] noNeed) {
        Map<String,String> map = JSON.toJavaObject((JSON) JSON.parse(json), Map.class);
        List<String> list = new ArrayList<>();
        map.keySet().forEach(x->{
            if (contain(noNeed,x) || x.contains("userinfo[")){
                list.add(x);
            }
        });
        list.forEach(x->map.remove(x));
        return map;
    }

    /**
     * 提供转化为csv文件的String
     * @param param 转化前的源map
     * @return csv文件的String内容
     */
    public static String getCsvString(Map<String, String> param){
        Set<String> strings = param.keySet();
        StringBuilder stringBuilder = new StringBuilder();
        strings.forEach(x->{
            stringBuilder.append(x).append(",");
        });
        StringBuilder stringBuilder1 = stringBuilder.deleteCharAt(stringBuilder.length()-1);
        stringBuilder.append("\n");
        strings.forEach(x->{
            stringBuilder1.append(param.get(x)).append(",");
        });
        return new String(stringBuilder1.deleteCharAt(stringBuilder.length()-1));
    }


    private static boolean contain(String[] strings, String s){
        List<String> list = Arrays.asList(strings);
        return list.contains(s.trim());
    }

}
