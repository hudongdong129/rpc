package com.study.hu.rpc.server.register;

public interface ServiceRegister {

    void register(ServiceObject serviceObject, String protocol, int port) throws Exception;

    ServiceObject getServiceObject(String name) throws Exception;
}
