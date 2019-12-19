package com.study.hu.rpc.server;

/**
 * @Author hudongdong
 * @Date 2019/12/19 13:56
 */
public abstract class RpcServer {

    protected int port;

    protected String protocol;

    protected RequestHandler handler;
}
