package com.jcl.burpspread.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageResultVO<T> {
    private PageInfoVO  pageInfoVO;
    private List<T> data;
    private Integer errorCode;
    private String message;

    public PageResultVO(Integer errorCode){
        this.errorCode =errorCode;
        this.message ="获取数据失败";
    }
    public static <T> PageResultVO<T> success(List<T> data, PageInfoVO pageInfo){
        return new PageResultVO<>(pageInfo,data,1,"获取page数据成功");
    }
    public static <T> PageResultVO<T> fail(){
        return new PageResultVO<>(0);
    }


    @NoArgsConstructor
    @Getter
    @Setter
    public static class PageInfoVO{
        private long total;
        private long totalPage;
        private int size;
        private int page;
        public PageInfoVO(long total,int size, int page){
            this.total = total;
            this.size =size;
            this.page =page;
            if (0L == total % (long)size) {
                this.totalPage = total / Long.valueOf(size + "");
            } else {
                this.totalPage = total / (long)size + 1L;
            }
        }
    }
}
