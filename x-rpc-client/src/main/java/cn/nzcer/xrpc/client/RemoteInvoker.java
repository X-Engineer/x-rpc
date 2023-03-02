package cn.nzcer.xrpc.client;

import cn.nzcer.xrpc.codec.Decoder;
import cn.nzcer.xrpc.codec.Encoder;
import cn.nzcer.xrpc.proto.Request;
import cn.nzcer.xrpc.proto.Response;
import cn.nzcer.xrpc.proto.ServiceDescriptor;
import cn.nzcer.xrpc.transport.TransportClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @project: x-rpc
 * @ClassName: RemoteInvoker
 * @author: nzcer
 * @creat: 2023/3/2 19:40
 * @description: 调用远程服务的代理类
 */
@Slf4j
public class RemoteInvoker implements InvocationHandler {
    private Class clazz;
    private Encoder encoder;
    private Decoder decoder;

    private TransportSelector selector;

    public RemoteInvoker(Class clazz, Encoder encoder, Decoder decoder, TransportSelector selector) {
        this.clazz = clazz;
        this.encoder = encoder;
        this.decoder = decoder;
        this.selector = selector;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        // 1.client 发起请求
        Request request = new Request();
        request.setService(ServiceDescriptor.from(clazz, method));
        request.setParameters(args);

        // 2.client 获得 response
        Response response = invokeRemote(request);
        if (response == null || response.getStatusCode() != 0) {
            // 调用失败
            throw new IllegalStateException("fail to invoke remote: " + response);
        }

        return response.getData();
    }

    /**
     * 调用远程方法
     *
     * @param request
     * @return
     */
    private Response invokeRemote(Request request) {
        TransportClient client = null;
        Response resp;
        try {
            client = selector.select();
            byte[] outBytes = encoder.encode(request);
            InputStream receive = client.write(new ByteArrayInputStream(outBytes));
            byte[] inBytes = IOUtils.readFully(receive, receive.available());
            resp = decoder.decode(inBytes, Response.class);
        } catch (IOException e) {
            log.warn(e.getMessage(), e);
            resp = new Response();
            resp.setStatusCode(1);
            resp.setStatusMessage("RpcClient got error:" + e.getClass() + ":" + e.getMessage());
        } finally {
            if (client != null) {
                selector.release(client);
            }
        }
        return resp;
    }
}
