package com.study.hu.rpc.server;

/**
 * @Author hudongdong
 * @Date 2019/12/19 13:56
 */
public abstract class RpcServer {

    protected int port;

    protected String protocol;

    protected RequestHandler handler;

    public RpcServer(int port, String protocol, RequestHandler handler) {
        this.port = port;
        this.protocol = protocol;
        this.handler = handler;
    }

    /**
     * @Author hudongdong
     * @Description 开启服务
     * @Date 11:14 2019/12/25
     * @Param []
     * @return void
     **/
    public abstract void start();

    /**
     * @Author hudongdong
     * @Description 停止服务
     * @Date 11:15 2019/12/25
     * @Param []
     * @return void
     **/
    public abstract void stop();

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public RequestHandler getHandler() {
        return handler;
    }

    public void setHandler(RequestHandler handler) {
        this.handler = handler;
    }
}
