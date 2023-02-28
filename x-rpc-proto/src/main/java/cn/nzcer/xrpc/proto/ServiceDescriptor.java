package cn.nzcer.xrpc.proto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @project: x-rpc
 * @ClassName: ServiceDescriptor
 * @author: nzcer
 * @creat: 2023/2/28 21:12
 * @description: 表示所需的服务
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDescriptor {
    private String clazz;
    private String method;
    private String returnType;
    private String[] parameterTypes;
}
