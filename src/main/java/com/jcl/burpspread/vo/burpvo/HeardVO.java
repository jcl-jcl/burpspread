package com.jcl.burpspread.vo.burpvo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HeardVO {
    private String methodName;
    private String url;
    private String cookies;
    private String contentType;
    private String platform;
    private Long userId;
    private String sysName;
}
