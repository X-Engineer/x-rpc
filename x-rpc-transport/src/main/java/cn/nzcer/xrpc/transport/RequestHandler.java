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
    void onRequest(InputStream receive, OutputStream toResp);
}
