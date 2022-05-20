
package com.qiji.network.net.exception;

public class TokenExpiredException extends BaseException {
    public TokenExpiredException(int errorCode, String cause) {
        super(errorCode, cause);
    }

}
