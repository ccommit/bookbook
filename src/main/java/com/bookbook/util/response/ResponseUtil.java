package com.bookbook.util.response;

public class ResponseUtil {

    private ResponseUtil() {}

    public static <T> CommonResponse<T> success(final int code, final T result) {
        return new CommonResponse<>(code, true, result);
    }
}
