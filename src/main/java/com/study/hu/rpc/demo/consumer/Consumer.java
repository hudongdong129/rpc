package com.study.hu.rpc.demo.consumer;

import com.study.hu.rpc.client.ClientStubProxyFactory;
import com.study.hu.rpc.client.net.NettyNetClient;
import com.study.hu.rpc.common.protocol.JavaSerializeMessageProtocol;
import com.study.hu.rpc.common.protocol.MessageProtocol;
import com.study.hu.rpc.demo.provider.DemoService;
import com.study.hu.rpc.discovery.ZookeeperServiceInfoDiscoverer;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author hudongdong
 * @Date 2019/12/25 10:04
 */
public class Consumer {

    public static void main(String[] args) {
        ClientStubProxyFactory factory = new ClientStubProxyFactory();
        // 设置服务发现者
        factory.setSid(new ZookeeperServiceInfoDiscoverer());
        // 设置支持的协议
        Map<String, MessageProtocol> supportMessageProtocol = new HashMap<>();
        supportMessageProtocol.put("javas", new JavaSerializeMessageProtocol());
        factory.setSupportMessageProtocols(supportMessageProtocol);
        // 设置网络层实现
        factory.setNetClient(new NettyNetClient());

        DemoService demoService = factory.getProxy(DemoService.class); // 获取远程服务代理
        String hello = demoService.sayHello("world"); // 执行远程方法
        System.out.println(hello);
        System.out.println(demoService.multiPoint(new Point(5, 10), 2));
    }
}
