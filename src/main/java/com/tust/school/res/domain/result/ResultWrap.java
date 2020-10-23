package com.tust.school.res.domain.result;

import lombok.Data;

/**
 * 返回类
 * @param <T>
 */
@Data
public class ResultWrap<T> {

    private boolean success;

    private T result;

    private Integer errorCode;

    private String errorMsg;

}
