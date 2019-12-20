package com.study.hu.rpc.server.register;

import org.I0Itec.zkclient.exception.ZkMarshallingError;
import org.I0Itec.zkclient.serialize.ZkSerializer;

import java.io.UnsupportedEncodingException;

/**
 * @Author hudongdong
 * @Date 2019/12/19 13:55
 */
public class MyZkSerializer implements ZkSerializer {
    String charset = "UTF-8";

    public byte[] serialize(Object o) throws ZkMarshallingError {
        try {
            return String.valueOf(o).getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new ZkMarshallingError(e);
        }
    }

    public Object deserialize(byte[] bytes) throws ZkMarshallingError {
        try {
            return new String(bytes, charset);
        } catch (Exception e) {
            throw new ZkMarshallingError(e);
        }
    }
}
