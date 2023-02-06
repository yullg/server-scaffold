package com.yullg.server.scaffold.web;

import org.springframework.lang.Nullable;

/**
 * 定义REST API中响应数据的基本结构
 */
public class RestResult<T> {

    public static final int CODE_OK = 0;
    public static final int CODE_NO = -1;
    public static final String MESSAGE_OK = "OK";
    public static final String MESSAGE_NO = "NO";

    public final int code;
    @Nullable
    public final String message;
    @Nullable
    public final T data;

    public RestResult(int code, @Nullable String message, @Nullable T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public RestResult(@Nullable T data) {
        this(CODE_OK, MESSAGE_OK, data);
    }

    public RestResult(int code, @Nullable String message) {
        this(code, message, null);
    }

}