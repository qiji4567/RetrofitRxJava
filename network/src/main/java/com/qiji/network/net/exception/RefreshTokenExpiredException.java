
package com.qiji.network.net.exception;

public class RefreshTokenExpiredException extends BaseException {

    public RefreshTokenExpiredException(int errorCode, String cause) {
        super(errorCode, cause);
    }
}
