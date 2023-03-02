package cn.nzcer.xrpc.server;

import cn.nzcer.xrpc.common.utils.ReflectionUtils;
import cn.nzcer.xrpc.proto.Request;
import cn.nzcer.xrpc.proto.ServiceDescriptor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @project: x-rpc
 * @ClassName: ServiceManager
 * @author: nzcer
 * @creat: 2023/3/2 9:22
 * @description: 管理 rpc 暴露的服务
 * 1.服务注册
 * 2.服务发现
 */
@Slf4j
public class ServiceManager {
    /**
     * Map 保存所有 service
     */
    private Map<ServiceDescriptor, ServiceInstance> services;

    public ServiceManager() {
        this.services = new ConcurrentHashMap<>();
    }

    /**
     * 服务注册
     *
     * @param interfaceClass 接口
     * @param bean           具体实现/子类的对象
     */
    public <T> void register(Class<T> interfaceClass, T bean) {
        /**
         * 将 interfaceClass 中的所有方法扫描出来，并与 bean 绑定，最后放入到 map 中
         */
        Method[] methods = ReflectionUtils.getPublicMethods(interfaceClass);
        for (Method method : methods) {
            ServiceDescriptor sDesc = ServiceDescriptor.from(interfaceClass, method);
            ServiceInstance instance = new ServiceInstance(bean, method);
            services.put(sDesc, instance);
            log.info("register service : {}; method : {}", sDesc.getClazz(), sDesc.getMethod());
        }
    }

    /**
     * 服务发现
     *
     * @param request 客户端发起的服务查询请求
     * @return
     */
    public ServiceInstance lookup(Request request) {
        ServiceDescriptor sdp = request.getService();
        return services.get(sdp);
    }
}
