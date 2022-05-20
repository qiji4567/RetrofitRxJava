package com.qiji.network.net.exception;

import com.qiji.network.net.common.ErrorCode;

/**
 * <pre>
 *   Created by zhpan on 2021/1/6.
 *   Description:
 * </pre>
 */
public class BaseException extends RuntimeException {

    protected final int errorCode;

    public BaseException(int errorCode, String cause) {
        super(ErrorCode.getErrorMessage(errorCode), new Throwable(cause));
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
