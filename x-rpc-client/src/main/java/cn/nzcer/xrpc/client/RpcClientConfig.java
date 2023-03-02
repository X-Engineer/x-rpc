package cn.nzcer.xrpc.client;

import cn.nzcer.xrpc.codec.Decoder;
import cn.nzcer.xrpc.codec.Encoder;
import cn.nzcer.xrpc.codec.JsonDecoder;
import cn.nzcer.xrpc.codec.JsonEncoder;
import cn.nzcer.xrpc.proto.Peer;
import cn.nzcer.xrpc.transport.HTTPTransportClient;
import cn.nzcer.xrpc.transport.TransportClient;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

/**
 * @project: x-rpc
 * @ClassName: RpcClientConfig
 * @author: nzcer
 * @creat: 2023/3/2 19:27
 * @description:
 */
@Data
public class RpcClientConfig {
    private Class<? extends TransportClient> transportClass = HTTPTransportClient.class;

    private Class<? extends Encoder> encoder = JsonEncoder.class;

    private Class<? extends Decoder> decoder = JsonDecoder.class;

    private Class<? extends TransportSelector> selectorClass = RandomTransportSelector.class;

    /**
     * 每个 peer 默认多少个连接
     */
    private int connectCount = 1;

    private List<Peer> servers = Arrays.asList(
            new Peer("127.0.0.1", 3000)
    );
}
