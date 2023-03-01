package cn.nzcer.xrpc.common.utils;

import lombok.SneakyThrows;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * @project: x-rpc
 * @ClassName: ReflectionUtils
 * @author: nzcer
 * @creat: 2023/2/28 21:39
 * @description: 反射工具类
 */
public class ReflectionUtils {
    /**
     * 根据 Class 创建对象
     *
     * @param clazz 待创建的类
     * @param <T>   对象类型
     * @return 创建好的对象
     */
    @SneakyThrows
    public static <T> T newInstance(Class<T> clazz) {
        return clazz.newInstance();
    }

    /**
     * 获取某个 class 的公有方法
     *
     * @param clazz
     * @return 当前类声明的公有方法
     */
    public static Method[] getPublicMethods(Class clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        List<Method> pMethods = new ArrayList<>();
        for (Method method : methods) {
            if (Modifier.isPublic(method.getModifiers())) {
                pMethods.add(method);
            }
        }
        return pMethods.toArray(new Method[0]);
    }

    /**
     * 调用指定对象的指定方法
     * @param obj 被调用方法的对象
     * @param method 被调用的方法
     * @param args 方法的参数
     * @return 方法的返回值
     */
    @SneakyThrows
    public static Object invoke(Object obj,
                                Method method,
                                Object... args) {
        return method.invoke(obj, args);
    }


}
