package com.study.hu.rpc.demo.provider;

import java.awt.*;

/**
 * @Author hudongdong
 * @Date 2019/12/25 10:22
 */
public class DemoServiceImpl implements DemoService {
    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }

    @Override
    public Point multiPoint(Point p, int multi) {
        p.x = p.x * multi;
        p.y = p.y * multi;
        return p;
    }
}
