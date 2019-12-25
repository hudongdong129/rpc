package com.study.hu.rpc.demo.provider;

import com.study.hu.rpc.common.protocol.JavaSerializeMessageProtocol;
import com.study.hu.rpc.server.NettyRpcServer;
import com.study.hu.rpc.server.RequestHandler;
import com.study.hu.rpc.server.RpcServer;
import com.study.hu.rpc.server.register.ServiceObject;
import com.study.hu.rpc.server.register.ServiceRegister;
import com.study.hu.rpc.server.register.ZookeeperExportServiceRegister;
import com.study.hu.rpc.util.PropertiesUtils;


/**
 * @Author hudongdong
 * @Date 2019/12/25 10:25
 */
public class Provider {

    public static void main(String[] args) throws Exception {
        int port = Integer.parseInt(PropertiesUtils.getProperties("rpc.port"));
        String protocol = PropertiesUtils.getProperties("rpc.protocol");
        // 服务注册
        ServiceRegister sr = new ZookeeperExportServiceRegister();
        DemoService ds = new DemoServiceImpl();
        ServiceObject so = new ServiceObject(DemoService.class.getName(), DemoService.class, ds);
        sr.register(so, protocol, port);

        RequestHandler requestHandler = new RequestHandler(new JavaSerializeMessageProtocol(), sr);
        RpcServer server = new NettyRpcServer(port, protocol, requestHandler);
        server.start();
        System.in.read(); // 按任意键退出
        server.stop();

    }
}
