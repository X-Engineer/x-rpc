package cn.nzcer.xrpc.client;

import cn.nzcer.xrpc.codec.Decoder;
import cn.nzcer.xrpc.codec.Encoder;
import cn.nzcer.xrpc.common.utils.ReflectionUtils;

import java.lang.reflect.Proxy;

/**
 * @project: x-rpc
 * @ClassName: RpcClient
 * @author: nzcer
 * @creat: 2023/3/2 19:33
 * @description:
 */
public class RpcClient {
    private RpcClientConfig config;
    private Encoder encoder;
    private Decoder decoder;

    private TransportSelector selector;

    public RpcClient() {
        this(new RpcClientConfig());
    }

    public RpcClient(RpcClientConfig config) {
        this.config = config;
        this.encoder = ReflectionUtils.newInstance(this.config.getEncoder());
        this.decoder = ReflectionUtils.newInstance(this.config.getDecoder());
        this.selector = ReflectionUtils.newInstance(this.config.getSelectorClass());

        this.selector.init(
                this.config.getServers(),
                this.config.getConnectCount(),
                this.config.getTransportClass()
        );
    }

    /**
     * 获取接口的代理对象
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getProxy(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(
                getClass().getClassLoader(),
                new Class[]{clazz},
                new RemoteInvoker(clazz, encoder, decoder, selector)
        );
    }
}
