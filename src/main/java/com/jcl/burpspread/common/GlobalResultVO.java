package com.jcl.burpspread.common;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public  class GlobalResultVO<T> {
    private int errorCode;
    private String message;
    private T data;

    public GlobalResultVO(int errorCode,String message) {
        this.message = message;
        this.errorCode = errorCode;
    }

    public static  GlobalResultVO fail(@Nullable String message){
        return new GlobalResultVO(801, message);
    }

    public static <T> GlobalResultVO<T> fail(@Nullable String message, T t){
        return new GlobalResultVO(822, message, t);
    }

    public static  GlobalResultVO success(@Nullable String message){
        return new GlobalResultVO(0, message);
    }

    public  static <T> GlobalResultVO<T> success(@Nullable String message, T data){
        return new GlobalResultVO(0, message, data);
    }

    public static  GlobalResultVO getInstance(){
        return new GlobalResultVO();
    }
}