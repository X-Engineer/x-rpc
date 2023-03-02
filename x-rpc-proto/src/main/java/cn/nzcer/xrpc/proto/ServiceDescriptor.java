package cn.nzcer.xrpc.proto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    public static ServiceDescriptor from(Class clazz, Method method) {
        ServiceDescriptor sDes = new ServiceDescriptor();
        sDes.setClazz(clazz.getName());
        sDes.setMethod(method.getName());
        sDes.setReturnType(method.getReturnType().getName());
        List<String> collect = Arrays.stream(method.getParameterTypes()).map(Class::getName).collect(Collectors.toList());
        String[] paramTypes = collect.toArray(new String[0]);
        sDes.setParameterTypes(paramTypes);
        return sDes;
    }

    /**
     * 重写 equals 和 hashCode 方法
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceDescriptor that = (ServiceDescriptor) o;

        if (!Objects.equals(clazz, that.clazz)) return false;
        if (!Objects.equals(method, that.method)) return false;
        if (!Objects.equals(returnType, that.returnType)) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(parameterTypes, that.parameterTypes);
    }

    @Override
    public int hashCode() {
        int result = clazz != null ? clazz.hashCode() : 0;
        result = 31 * result + (method != null ? method.hashCode() : 0);
        result = 31 * result + (returnType != null ? returnType.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(parameterTypes);
        return result;
    }

    @Override
    public String toString() {
        return "ServiceDescriptor{" +
                "clazz='" + clazz + '\'' +
                ", method='" + method + '\'' +
                ", returnType='" + returnType + '\'' +
                ", parameterTypes=" + Arrays.toString(parameterTypes) +
                '}';
    }
}
