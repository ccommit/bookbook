package com.bookbook.util.response;

import com.fasterxml.jackson.annotation.JsonInclude;

public record CommonResponse<T>(int code, boolean success, @JsonInclude(JsonInclude.Include.NON_NULL) T result) {}
