package cn.nzcer.xrpc.server;

import cn.nzcer.xrpc.codec.Decoder;
import cn.nzcer.xrpc.codec.Encoder;
import cn.nzcer.xrpc.codec.JsonDecoder;
import cn.nzcer.xrpc.codec.JsonEncoder;
import cn.nzcer.xrpc.transport.HTTPTransportServer;
import cn.nzcer.xrpc.transport.TransportServer;
import lombok.Data;

/**
 * @project: x-rpc
 * @ClassName: RpcServerConfig
 * @author: nzcer
 * @creat: 2023/3/2 9:04
 * @description:
 * 1.使用哪个网络模块
 * 2.使用哪个序列化的实现
 * 3.启动后监听什么端口
 */
@Data
public class RpcServerConfig {
    /**
     * 网络模块默认使用 HTTP
     */
    private Class<? extends TransportServer> transportClass = HTTPTransportServer.class;

    /**
     * 序列化模块默认使用 Json
     */
    private Class<? extends Encoder> encoderClass = JsonEncoder.class;
    private Class<? extends Decoder> decoderClass = JsonDecoder.class;

    private int port = 3000;
}
