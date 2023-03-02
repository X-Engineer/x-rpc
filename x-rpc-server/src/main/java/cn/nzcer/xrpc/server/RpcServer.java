package cn.nzcer.xrpc.server;

import cn.nzcer.xrpc.codec.Decoder;
import cn.nzcer.xrpc.codec.Encoder;
import cn.nzcer.xrpc.common.utils.ReflectionUtils;
import cn.nzcer.xrpc.proto.Request;
import cn.nzcer.xrpc.proto.Response;
import cn.nzcer.xrpc.transport.RequestHandler;
import cn.nzcer.xrpc.transport.TransportServer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @project: x-rpc
 * @ClassName: RpcServer
 * @author: nzcer
 * @creat: 2023/3/2 16:03
 * @description:
 */
@Slf4j
public class RpcServer {
    private RpcServerConfig config;
    private TransportServer net;

    private Encoder encoder;
    private Decoder decoder;

    private ServiceManager serviceManager;

    private ServiceInvoker serviceInvoker;

    public RpcServer(RpcServerConfig config) {
        // config 由调用方传递过来
        this.config = config;

        // net
        this.net = ReflectionUtils.newInstance(config.getTransportClass());
        this.net.init(config.getPort(), this.handler);

        // codec
        this.encoder = ReflectionUtils.newInstance(config.getEncoderClass());
        this.decoder = ReflectionUtils.newInstance(config.getDecoderClass());

        // service
        this.serviceManager = new ServiceManager();
        this.serviceInvoker = new ServiceInvoker();
    }

    public <T> void register(Class<T> interfaceClass, T bean) {
        serviceManager.register(interfaceClass, bean);
    }

    public void start() {
        this.net.start();
    }

    public void stop() {
        this.net.stop();
    }

    // 实现 handler
    private RequestHandler handler = new RequestHandler() {
        @Override
        public void onRequest(InputStream receive, OutputStream toResp) {
            Response response = new Response();
            try {
                byte[] inBytes = IOUtils.readFully(receive, receive.available());
                Request request = decoder.decode(inBytes, Request.class);
                log.info("get request: {}", request);
                ServiceInstance sis = serviceManager.lookup(request);
                Object ret = serviceInvoker.invoke(sis, request);
                response.setData(ret);

            } catch (Exception e) {
                log.warn(e.getMessage(), e);
                response.setStatusCode(1);
                response.setStatusMessage("RpcServer got error:" + e.getClass().getName() + ":" + e.getMessage());
            } finally {
                byte[] outBytes = encoder.encode(response);
                try {
                    IOUtils.write(outBytes, toResp);
                    log.info("response client");
                } catch (IOException e) {
                    log.warn(e.getMessage(), e);
                }
            }
        }
    };
}
