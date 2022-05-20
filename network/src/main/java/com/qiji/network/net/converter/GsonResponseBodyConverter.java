/*
 * Copyright (C) 2015 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.qiji.network.net.converter;

import com.google.gson.TypeAdapter;
import com.qiji.network.net.exception.NoDataExceptionException;
import com.qiji.network.net.exception.RefreshTokenExpiredException;
import com.qiji.network.net.exception.ServerResponseException;
import com.qiji.network.net.exception.TokenExpiredException;
import com.qiji.network.net.module.BasicResponse;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

import static com.qiji.network.net.common.ErrorCode.REFRESH_TOKEN_EXPIRED;
import static com.qiji.network.net.common.ErrorCode.SUCCESS;
import static com.qiji.network.net.common.ErrorCode.TOKEN_EXPIRED;

final class GsonResponseBodyConverter<T> implements Converter<ResponseBody, Object> {

    private final TypeAdapter<T> adapter;

    GsonResponseBodyConverter(TypeAdapter<T> adapter) {
        this.adapter = adapter;
    }

    @Override
    public  Object convert(ResponseBody value) throws IOException {
        try {
            BasicResponse response = (BasicResponse) adapter.fromJson(value.charStream());
            if (response.getErrorCode() == SUCCESS) {
                if (response.getData() == null)
                    throw new NoDataExceptionException();
                return response.getData();
            } else if (response.getErrorCode() == TOKEN_EXPIRED) {
                throw new TokenExpiredException(response.getErrorCode(), response.getErrorMsg());
            } else if (response.getErrorCode() == REFRESH_TOKEN_EXPIRED) {
                throw new RefreshTokenExpiredException(response.getErrorCode(), response.getErrorMsg());
            } else if (response.getErrorCode() != SUCCESS) {
                // 特定 API 的错误，在相应的 DefaultObserver 的 onError 的方法中进行处理
                throw new ServerResponseException(response.getErrorCode(), response.getErrorMsg());
            }
        } finally {
            value.close();
        }
        return null;
    }
}
