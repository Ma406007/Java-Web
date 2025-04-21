package org.example.exception;

import lombok.extern.slf4j.Slf4j;
import org.example.pojo.Result;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器:定义一个类并在类上加上一个注解@RestControllerAdvice,加上这个注解就代表我们定义了一个全局异常处理器
 * 在全局异常处理器当中需要定义一个方法来捕获异常,在这个方法上需要加上注解@ExceptionHandler
 * 可以通过@ExceptionHandler注解当中的value属性来指定我们要捕获的是哪一类型的异常
 * */
@Slf4j
@RestControllerAdvice//@RestControllerAdvice = @ControllerAdvice + @ResponseBody 处理异常的方法返回值会转换为json后再响应给前端,表示当前类为全局异常处理器
public class GlobalExceptionHandler {
    @ExceptionHandler//指定可以捕获哪种类型的异常进行处理
    public Result handleException(Exception exception) {
        log.error("程序出错啦~", exception);
        return Result.error("出错啦，请联系管理员~");
    }

    @ExceptionHandler
    public Result handleDuplicateKeyException(DuplicateKeyException exception) {
        log.error("程序出错啦~", exception);

        //获取错误信息
        String message = exception.getMessage();
        int i = message.indexOf("Duplicate entry");
        String errorMessage = message.substring(i);
        String[] arr = errorMessage.split(" ");

        return Result.error(arr[2] + "已存在");
    }
}
