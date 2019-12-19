package com.study.hu.rpc.discovery;

import lombok.Data;

/**
 * @Author hudongdong
 * @Date 2019/12/19 13:36
 */
@Data
public class ServiceInfo {

    private String name;
    private String protocol;
    private String address;
}
