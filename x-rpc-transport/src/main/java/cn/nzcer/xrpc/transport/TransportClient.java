package cn.nzcer.xrpc.transport;

import cn.nzcer.xrpc.proto.Peer;

import java.io.InputStream;

/**
 * @project: x-rpc
 * @ClassName: TransportClient
 * @author: nzcer
 * @creat: 2023/3/1 21:59
 * @description: 1.创建连接
 * 2.发送数据，并且等待响应
 * 3.关闭连接
 */
public interface TransportClient {
    void connect(Peer peer);

    InputStream write(InputStream data);

    void close();
}
