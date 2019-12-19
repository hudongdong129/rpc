package com.study.hu.rpc.common.protocol;

/**
 * @Author hudongdong
 * @Date 2019/12/19 14:34
 */
public enum  Status {
    SUCCESS(200, "SUCCESS"),
    ERROR(500, "ERROR"),
    NOT_FOUND(404, "NOT FOUND");

    private int code;

    private String message;

    Status(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
