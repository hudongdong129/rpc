package com.study.hu.rpc.server.register;


import com.alibaba.fastjson.JSON;
import com.study.hu.rpc.discovery.ServiceInfo;
import com.study.hu.rpc.util.PropertiesUtils;
import org.I0Itec.zkclient.ZkClient;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLEncoder;

/**
 * @Author hudongdong
 * @Date 2019/12/25 10:32
 */
public class ZookeeperExportServiceRegister extends DefaultServiceRegister implements ServiceRegister {

    private ZkClient zkClient;

    private String centerRootPath = "/Rpc-framework";

    public ZookeeperExportServiceRegister() {
        String addr = PropertiesUtils.getProperties("zk.address");
        zkClient = new ZkClient(addr);
        zkClient.setZkSerializer(new MyZkSerializer());
    }

    @Override
    public void register(ServiceObject serviceObject, String protocol, int port) throws Exception {
        super.register(serviceObject, protocol, port);
        ServiceInfo serviceInfo = new ServiceInfo();
        String host = InetAddress.getLocalHost().getHostAddress();
        String address = host + ":" + port;
        serviceInfo.setAddress(address);
        serviceInfo.setName(serviceObject.getInterf().getName());
        serviceInfo.setProtocol(protocol);
        this.exportService(serviceInfo);
    }

    private void exportService(ServiceInfo serviceInfo) {
        String serviceName = serviceInfo.getName();
        String uri = JSON.toJSONString(serviceInfo);
        try {
            uri = URLEncoder.encode(uri, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String servicePath = centerRootPath + "/" + serviceName + "/service";
        if (!zkClient.exists(servicePath)) {
            zkClient.createPersistent(servicePath, true);
        }
        String uriPath = servicePath + "/" + uri;
        if (zkClient.exists(uriPath)) {
            zkClient.delete(uriPath);
        }
        zkClient.createEphemeral(uriPath);
    }

}
