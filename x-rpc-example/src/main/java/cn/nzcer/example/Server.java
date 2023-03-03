package cn.nzcer.example;

import cn.nzcer.xrpc.server.RpcServer;

/**
 * @project: x-rpc
 * @ClassName: Server
 * @author: nzcer
 * @creat: 2023/3/3 11:56
 * @description:
 */
public class Server {
    public static void main(String[] args) {
        RpcServer server = new RpcServer();
        server.register(CalcService.class, new CalcServiceImpl());
        server.start();
    }
}
