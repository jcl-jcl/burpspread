package com.jcl.burpspread.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "databuckets")
public class DataBucketsDO extends BaseDO {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 方法名称
     */
    private String methodname="";

    /**
     * 患者userid
     */
    private Long userid =0L;

    /**
     * 患者Id
     */
    private Long patientid =0L;

    /**
     * databucket层的系统名
     */
    private String systemname="";

    /**
     * 完整的请求url
     */
    private String url="";

    /**
     * 参数类型
     */
    private Integer contenttype=1;

    /**
     * 请求方式
     */
    private String requestmethod="";

    /**
     * 用户cookie
     */
    private String cookies="";

    /**
     * 设备端-app，touch
     */
    private String platform="";

    /**
     * 创建时间
     */
    private Date ctime = new Date();;

    /**
     * 修改时间
     */
    private Date utime = new Date();;

    /**
     * 版本号
     */
    private Integer ver=1;

    /**
     * 请求参数
     */
    private String params="";

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取方法名称
     *
     * @return methodname - 方法名称
     */
    public String getMethodname() {
        return methodname;
    }

    /**
     * 设置方法名称
     *
     * @param methodname 方法名称
     */
    public void setMethodname(String methodname) {
        this.methodname = methodname;
    }

    /**
     * 获取患者userid
     *
     * @return userid - 患者userid
     */
    public Long getUserid() {
        return userid;
    }

    /**
     * 设置患者userid
     *
     * @param userid 患者userid
     */
    public void setUserid(Long userid) {
        this.userid = userid;
    }

    /**
     * 获取患者Id
     *
     * @return patientid - 患者Id
     */
    public Long getPatientid() {
        return patientid;
    }

    /**
     * 设置患者Id
     *
     * @param patientid 患者Id
     */
    public void setPatientid(Long patientid) {
        this.patientid = patientid;
    }

    /**
     * 获取databucket层的系统名
     *
     * @return systemname - databucket层的系统名
     */
    public String getSystemname() {
        return systemname;
    }

    /**
     * 设置databucket层的系统名
     *
     * @param systemname databucket层的系统名
     */
    public void setSystemname(String systemname) {
        this.systemname = systemname;
    }

    /**
     * 获取完整的请求url
     *
     * @return url - 完整的请求url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置完整的请求url
     *
     * @param url 完整的请求url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取参数类型
     *
     * @return contenttype - 参数类型
     */
    public Integer getContenttype() {
        return contenttype;
    }

    /**
     * 设置参数类型
     *
     * @param contenttype 参数类型
     */
    public void setContenttype(Integer contenttype) {
        this.contenttype = contenttype;
    }

    /**
     * 获取请求方式
     *
     * @return requestmethod - 请求方式
     */
    public String getRequestmethod() {
        return requestmethod;
    }

    /**
     * 设置请求方式
     *
     * @param requestmethod 请求方式
     */
    public void setRequestmethod(String requestmethod) {
        this.requestmethod = requestmethod;
    }

    /**
     * 获取用户cookie
     *
     * @return cookies - 用户cookie
     */
    public String getCookies() {
        return cookies;
    }

    /**
     * 设置用户cookie
     *
     * @param cookies 用户cookie
     */
    public void setCookies(String cookies) {
        this.cookies = cookies;
    }

    /**
     * 获取设备端-app，touch
     *
     * @return platform - 设备端-app，touch
     */
    public String getPlatform() {
        return platform;
    }

    /**
     * 设置设备端-app，touch
     *
     * @param platform 设备端-app，touch
     */
    public void setPlatform(String platform) {
        this.platform = platform;
    }

    /**
     * 获取创建时间
     *
     * @return ctime - 创建时间
     */
    public Date getCtime() {
        return ctime;
    }

    /**
     * 设置创建时间
     *
     * @param ctime 创建时间
     */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    /**
     * 获取修改时间
     *
     * @return utime - 修改时间
     */
    public Date getUtime() {
        return utime;
    }

    /**
     * 设置修改时间
     *
     * @param utime 修改时间
     */
    public void setUtime(Date utime) {
        this.utime = utime;
    }

    /**
     * 获取版本号
     *
     * @return ver - 版本号
     */
    public Integer getVer() {
        return ver;
    }

    /**
     * 设置版本号
     *
     * @param ver 版本号
     */
    public void setVer(Integer ver) {
        this.ver = ver;
    }

    /**
     * 获取请求参数
     *
     * @return params - 请求参数
     */
    public String getParams() {
        return params;
    }

    /**
     * 设置请求参数
     *
     * @param params 请求参数
     */
    public void setParams(String params) {
        this.params = params;
    }
}