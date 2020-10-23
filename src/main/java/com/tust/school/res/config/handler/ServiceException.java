package com.tust.school.res.config.handler;

import lombok.Data;

@Data
public class ServiceException extends RuntimeException {

    private ServiceExceptionEnum serviceExceptionEnum;

    public ServiceException(ServiceExceptionEnum exceptionEnum) {
        this.serviceExceptionEnum = exceptionEnum;
    }

}
