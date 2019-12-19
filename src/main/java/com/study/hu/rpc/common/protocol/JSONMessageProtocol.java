package com.study.hu.rpc.common.protocol;

import com.alibaba.fastjson.JSON;

/**
 * @Author hudongdong
 * @Date 2019/12/19 15:22
 */
public class JSONMessageProtocol implements MessageProtocol {


    @Override
    public byte[] marshallingRequest(Request req) throws Exception {
        Request temp = new Request();
        temp.setServiceName(req.getServiceName());
        temp.setMethod(req.getMethod());
        temp.setHeaders(req.getHeaders());
        temp.setParameterTypes(req.getParameterTypes());
        if (req.getParameters() != null) {
            Object[] params = req.getParameters();
            Object[] serizeParams = new Object[params.length];
            for (int i = 0; i < params.length; i++) {
                serizeParams[i] = JSON.toJSONString(params[i]);
            }
            temp.setParameters(serizeParams);
        }
        return JSON.toJSONBytes(temp);
    }

    @Override
    public Request unmarshallingRequest(byte[] data) throws Exception {
        return null;
    }

    @Override
    public byte[] marshallingResponse(Response rsp) throws Exception {
        return new byte[0];
    }

    @Override
    public Response unmarshallingResponse(byte[] data) throws Exception {
        return null;
    }
}
