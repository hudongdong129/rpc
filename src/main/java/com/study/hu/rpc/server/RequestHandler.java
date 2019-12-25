package com.study.hu.rpc.server;

import com.study.hu.rpc.common.protocol.MessageProtocol;
import com.study.hu.rpc.common.protocol.Request;
import com.study.hu.rpc.common.protocol.Response;
import com.study.hu.rpc.common.protocol.Status;
import com.study.hu.rpc.server.register.ServiceObject;
import com.study.hu.rpc.server.register.ServiceRegister;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author hudongdong
 * @Date 2019/12/19 14:20
 */
public class RequestHandler {

    private MessageProtocol protocol;

    private ServiceRegister serviceRegister;

    public RequestHandler(MessageProtocol protocol, ServiceRegister serviceRegister) {
        this.protocol = protocol;
        this.serviceRegister = serviceRegister;
    }

    public byte[] handleRequest(byte[] data) throws Exception {
        // 1.解组消息
        Request req = this.protocol.unmarshallingRequest(data);
        // 2.查找服务对象
        ServiceObject so = this.serviceRegister.getServiceObject(req.getServiceName());

        Response rsp = null;
        if (so == null) {
            rsp = new Response(Status.NOT_FOUND);
        } else {
            // 3.反射调用对应的方法
            try {
                Method m = so.getInterf().getMethod(req.getMethod(), req.getParameterTypes());
                Object returnValue = m.invoke(so.getObj(), req.getParameters());
                rsp = new Response(Status.SUCCESS);
                rsp.setReturnValue(returnValue);
            } catch (NoSuchMethodException | SecurityException | IllegalAccessException |
            IllegalArgumentException | InvocationTargetException e)  {
                rsp = new Response(Status.ERROR);
                rsp.setException(e);
            }
        }
        // 4.编组响应消息
        return this.protocol.marshallingResponse(rsp);
    }

    public MessageProtocol getProtocol() {
        return protocol;
    }

    public void setProtocol(MessageProtocol protocol) {
        this.protocol = protocol;
    }

    public ServiceRegister getServiceRegister() {
        return serviceRegister;
    }

    public void setServiceRegister(ServiceRegister serviceRegister) {
        this.serviceRegister = serviceRegister;
    }
}
