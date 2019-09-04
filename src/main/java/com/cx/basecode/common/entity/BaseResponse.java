package com.cx.basecode.common.entity;

import org.springframework.http.HttpStatus;

import java.util.HashMap;

/**
 * @author: cx
 * @date: 2019/9/4
 */
public class BaseResponse extends HashMap<String, Object> {
    public BaseResponse code(HttpStatus status) {
        this.put("code", status.value());
        return this;
    }

    public BaseResponse message(String message) {
        this.put("message", message);
        return this;
    }

    public BaseResponse data(Object data) {
        this.put("data", data);
        return this;
    }

    public BaseResponse success() {
        this.code(HttpStatus.OK);
        return this;
    }

    public BaseResponse fail() {
        this.code(HttpStatus.INTERNAL_SERVER_ERROR);
        return this;
    }

    @Override
    public BaseResponse put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
