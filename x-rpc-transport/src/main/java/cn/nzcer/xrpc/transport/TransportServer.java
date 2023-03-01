package cn.nzcer.xrpc.transport;

/**
 * @project: x-rpc
 * @ClassName: TransportServer
 * @author: nzcer
 * @creat: 2023/3/1 22:02
 * @description:
 * 1.启动服务，监听端口
 * 2.等待请求，处理请求
 * 3.关闭监听
 */
public interface TransportServer {
    void init(int port, RequestHandler handler);
    void start();
    void stop();
}
