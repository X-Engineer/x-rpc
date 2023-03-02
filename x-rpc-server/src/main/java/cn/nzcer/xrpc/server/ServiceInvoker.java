package cn.nzcer.xrpc.server;

import cn.nzcer.xrpc.common.utils.ReflectionUtils;
import cn.nzcer.xrpc.proto.Request;

/**
 * @project: x-rpc
 * @ClassName: ServiceInvoker
 * @author: nzcer
 * @creat: 2023/3/2 16:01
 * @description: 调用具体服务
 */
public class ServiceInvoker {
    public Object invoke(ServiceInstance service,
                         Request request) {
        return ReflectionUtils.invoke(service.getTarget(), service.getMethod(), request.getParameters());
    }
}
