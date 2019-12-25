package com.study.hu.rpc.demo.provider;

import java.awt.*;

public interface DemoService {

    String sayHello(String name);

    Point multiPoint(Point p, int multi);
}
