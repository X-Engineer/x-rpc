package cn.nzcer.xrpc.server;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Method;

/**
 * @project: x-rpc
 * @ClassName: ServiceInstance
 * @author: nzcer
 * @creat: 2023/3/2 9:20
 * @description: 表示一个具体的服务
 * 1. 这个服务由哪个对象提供
 * 2. 对象的哪个方法暴露成一个服务
 */
@Data
@AllArgsConstructor
public class ServiceInstance {
    private Object target;
    private Method method;
}
