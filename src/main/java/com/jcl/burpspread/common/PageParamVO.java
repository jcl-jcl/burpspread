package com.jcl.burpspread.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PageParamVO<T> {
    private int nowPage;
    private int pageSize;
    private T paramVO;
}
