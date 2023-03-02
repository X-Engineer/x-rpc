package cn.nzcer.xrpc.client;

import cn.nzcer.xrpc.proto.Peer;
import cn.nzcer.xrpc.transport.TransportClient;

import java.util.List;

/**
 * @project: x-rpc
 * @ClassName: TransportSelector
 * @author: nzcer
 * @creat: 2023/3/2 18:41
 * @description: 表示选择连接哪个 server
 */
public interface TransportSelector {

    /**
     * 初始化 selector
     * @param peers 可以连接的 server 端点信息
     * @param count client 与 server 建立多少个连接
     * @param clazz client 实现 class
     */
    void init(List<Peer> peers, int count, Class<? extends TransportClient> clazz);

    /**
     * 选择一个 transport 与 server 做交互
     *
     * @return 网络 client
     */
    TransportClient select();

    /**
     * 释放用完的 client
     *
     * @param client
     */
    void release(TransportClient client);

    void close();
}
