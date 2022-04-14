package com.jcl.burpspread.vo.burpvo;

import com.jcl.burpspread.domain.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class SaveRequestVO extends BaseDO {
    private Map<String, String> param = null;
    private byte contentType= 0;
    private String method= null;
    private List<String> headers= null;
    private String host= null;

}
