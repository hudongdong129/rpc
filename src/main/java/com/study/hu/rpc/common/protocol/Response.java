package com.study.hu.rpc.common.protocol;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author hudongdong
 * @Date 2019/12/19 14:33
 */
public class Response implements Serializable {

    private static final long serialVersionUID = -4444706353612846553L;

    private Status status;

    private Map<String, String> headers = new HashMap<String, String>();

    private Object returnValue;

    private Exception exception;

    public Response(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public Object getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(Object returnValue) {
        this.returnValue = returnValue;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public void setHeader(String key, String value) {
        this.headers.put(key, value);
    }

    public String getHeader(String key) {
        return this.headers == null ? null : this.headers.get(key);
    }
}
