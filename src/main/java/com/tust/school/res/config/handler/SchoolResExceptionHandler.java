package com.tust.school.res.config.handler;

import com.tust.school.res.domain.result.ResultWrap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@ControllerAdvice
@Slf4j
public class SchoolResExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultWrap exceptionHandler(Exception e) {
        ResultWrap resultWrap = new ResultWrap();
        resultWrap.setSuccess(false);
        if (e instanceof ServiceException) {
            ServiceException serviceException = (ServiceException) e;
            resultWrap.setErrorCode(serviceException.getServiceExceptionEnum().getErrorCode());
            resultWrap.setErrorMsg(serviceException.getServiceExceptionEnum().getMessage());
        } else if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException methodArgumentNotValidException = (MethodArgumentNotValidException) e;
            List<FieldError> fieldErrors = methodArgumentNotValidException.getBindingResult().getFieldErrors();
            StringBuilder message = new StringBuilder();
            if (!CollectionUtils.isEmpty(fieldErrors)) {
                for (FieldError fieldError : fieldErrors) {
                    String defaultMessage = fieldError.getDefaultMessage();
                    message.append(defaultMessage);
                    message.append(";");
                }
            }
            resultWrap.setErrorMsg(message.toString());
        }
        log.error("{}", e);
        return resultWrap;
    }

}
