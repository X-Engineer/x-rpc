package cn.nzcer.xrpc.transport;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * @project: x-rpc
 * @ClassName: RequestHandler
 * @author: nzcer
 * @creat: 2023/3/1 22:03
 * @description: 处理网络请求的 handler
 */
public interface RequestHandler {
    /**
     *
     * @param receive 接收到客户端的请求
     * @param toResp 返回给客户端的响应
     */
    void onRequest(InputStream receive, OutputStream toResp);
}
