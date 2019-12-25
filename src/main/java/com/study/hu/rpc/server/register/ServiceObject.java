package com.study.hu.rpc.server.register;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author hudongdong
 * @Date 2019/12/25 10:29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceObject {

    private String name;

    private Class<?> interf;

    private Object obj;

}
