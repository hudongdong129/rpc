package com.study.hu.rpc.client;

import com.study.hu.rpc.client.net.NetClient;
import com.study.hu.rpc.common.protocol.MessageProtocol;
import com.study.hu.rpc.common.protocol.Request;
import com.study.hu.rpc.common.protocol.Response;
import com.study.hu.rpc.discovery.ServiceInfo;
import com.study.hu.rpc.discovery.ServiceInfoDiscoverer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @Author hudongdong
 * @Date 2019/12/19 13:33
 */
public class ClientStubProxyFactory {

    private ServiceInfoDiscoverer sid;

    private Map<String, MessageProtocol> supportMessageProtocols;

    private NetClient netClient;

    private Map<Class<?>, Object> objectCache = new HashMap<>();

    public <T> T getProxy(Class<T> interf) {
        T obj = (T) this.objectCache.get(interf);
        if (obj == null) {
            obj = (T) Proxy.newProxyInstance(interf.getClassLoader(), new Class<?>[]{interf},
                    new ClientStubInvocationHandler(interf));
            this.objectCache.put(interf, obj);
        }
        return obj;
    }

    private class ClientStubInvocationHandler implements InvocationHandler {
        // 被代理的对象
        private Class<?> interf;

        private Random random = new Random();

        public ClientStubInvocationHandler(Class<?> interf) {
            super();
            this.interf = interf;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.getName().equals("toString")) {
                return proxy.getClass().toString();
            }
            if (method.getName().equals("hashCode")) {
                return 0;
            }
            // 1.获得服务信息
            String serviceName = this.interf.getName();
            List<ServiceInfo> sinfos = sid.getServiceInfo(serviceName);

            if (sinfos == null || sinfos.size() == 0) {
                throw new Exception("远程服务不存在");
            }
            // 随机选择一个服务提供者（负载均衡）
            ServiceInfo serviceInfo = sinfos.get(random.nextInt(sinfos.size()));

            // 提供一个request对象
            Request req = new Request();
            req.setServiceName(serviceInfo.getName());
            req.setMethod(method.getName());
            req.setParameterTypes(method.getParameterTypes());
            req.setParameters(args);

            // 协议层
            MessageProtocol protocol = supportMessageProtocols.get(serviceInfo.getProtocol());
            // 编组请求
            byte[] data = protocol.marshallingRequest(req);
            // 调用网络层发送请求
            byte[] repData = netClient.sendRequest(data, serviceInfo);
            // 解组响应
            Response response = protocol.unmarshallingResponse(repData);
            // 处理结果
            if (response.getException() != null) {
                throw response.getException();
            }
            return response.getReturnValue();
        }
    }

    public void setSid(ServiceInfoDiscoverer sid) {
        this.sid = sid;
    }

    public void setSupportMessageProtocols(Map<String, MessageProtocol> supportMessageProtocols) {
        this.supportMessageProtocols = supportMessageProtocols;
    }

    public void setNetClient(NetClient netClient) {
        this.netClient = netClient;
    }
}
