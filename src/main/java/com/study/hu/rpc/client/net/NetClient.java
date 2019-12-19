package com.study.hu.rpc.client.net;

import com.study.hu.rpc.discovery.ServiceInfo;

public interface NetClient {

    byte[] sendRequest(byte[] data, ServiceInfo sinfo) throws Throwable;
}
