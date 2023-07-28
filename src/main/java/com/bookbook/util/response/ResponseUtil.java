package com.bookbook.util.response;

public class ResponseUtil {

    private ResponseUtil() {}

    public static <T> CommonResponse<T> success(int code, T result) {
        return new CommonResponse<>(code, true, result);
    }
}
