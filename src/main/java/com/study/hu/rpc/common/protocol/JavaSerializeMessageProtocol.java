package com.study.hu.rpc.common.protocol;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @Author hudongdong
 * @Date 2019/12/25 10:09
 */
public class JavaSerializeMessageProtocol implements MessageProtocol {

    private byte[] serialize(Object object) throws Exception {
        ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOutputStream);
        out.writeObject(object);
        return byteOutputStream.toByteArray();
    }

    @Override
    public byte[] marshallingRequest(Request req) throws Exception {
        return this.serialize(req);
    }

    @Override
    public Request unmarshallingRequest(byte[] data) throws Exception {
        ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(data));
        return (Request) in.readObject();
    }

    @Override
    public byte[] marshallingResponse(Response rsp) throws Exception {
        return this.serialize(rsp);
    }

    @Override
    public Response unmarshallingResponse(byte[] data) throws Exception {
        ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(data));
        return (Response) in.readObject();
    }
}
