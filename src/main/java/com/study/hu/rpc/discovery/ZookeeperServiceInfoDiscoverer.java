package com.study.hu.rpc.discovery;

import com.alibaba.fastjson.JSON;
import com.study.hu.rpc.server.register.MyZkSerializer;
import com.study.hu.rpc.util.PropertiesUtils;
import org.I0Itec.zkclient.ZkClient;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author hudongdong
 * @Date 2019/12/19 13:41
 */
public class ZookeeperServiceInfoDiscoverer implements ServiceInfoDiscoverer {

    ZkClient client;

    private String centerRootPath = "/Rpc-framework";

    public ZookeeperServiceInfoDiscoverer() {
        String addr = PropertiesUtils.getProperties("zk.address");
        client = new ZkClient(addr);
        client.setZkSerializer(new MyZkSerializer());
    }

    public List<ServiceInfo> getServiceInfo(String name) {
        String servicePath = centerRootPath + "/" + name + "/service";
        List<String> children = client.getChildren(servicePath);
        List<ServiceInfo> resources = new ArrayList<ServiceInfo>();
        for (String child : children) {
            try {
                String deCh = URLDecoder.decode(child, "UTF-8");
                ServiceInfo serviceInfo = JSON.parseObject(deCh, ServiceInfo.class);
                resources.add(serviceInfo);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return resources;
    }
}
