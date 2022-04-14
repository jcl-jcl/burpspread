package com.jcl.burpspread.common;//package com.jcl.testbackstage.common;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.apache.catalina.connector.ClientAbortException;

//@Slf4j
//@ResponseBody
//@ControllerAdvice
//public class GlobalExceptionHandler {
//
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler(ServiceException.class)
//    public GlobalResultVO serviceExceptionHandler(ServiceException se){
//        log.error(se.getMessage());
//        return GlobalResultVO.fail(se.getMessage());
//    }
//
//    @ResponseStatus(HttpStatus.BAD_GATEWAY)
//    @ExceptionHandler(Exception.class)
//    public GlobalResultVO exceptionHandler(Exception e){
//        if ("org.apache.catalina.connector.ClientAbortException".equals(e.getClass().getName())) {
//            return null;
//        }
//        log.error(e.getStackTrace().toString());
//        log.error(e.getMessage());
//        return GlobalResultVO.fail("非业务异常 系统繁忙，请稍后重试");
//    }
//
//    @ResponseStatus(HttpStatus.NOT_EXTENDED)
//    @ExceptionHandler(Throwable.class)
//    public GlobalResultVO throwHandler(Throwable th){
//        log.error(th.getMessage());
//        return GlobalResultVO.fail("系统错误 系统繁忙，请稍后重试");
//    }
//
//
//}
