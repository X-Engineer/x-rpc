package cn.nzcer.xrpc.server;

import cn.nzcer.xrpc.common.utils.ReflectionUtils;
import cn.nzcer.xrpc.proto.Request;
import cn.nzcer.xrpc.proto.ServiceDescriptor;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

import static junit.framework.Assert.assertNotNull;


/**
 * @project: x-rpc
 * @ClassName: ServiceManagerTest
 * @author: nzcer
 * @creat: 2023/3/2 14:19
 * @description:
 */
public class ServiceManagerTest {
    ServiceManager serviceManager;

    @Before
    public void init() {
        serviceManager = new ServiceManager();
        register();
    }

    @Test
    public void register() {
        TestInterface instance = new TestClass();
        serviceManager.register(TestInterface.class, instance);
    }

    @Test
    public void lookup() throws NoSuchMethodException {
        //Method method = ReflectionUtils.getPublicMethods(TestInterface.class)[0];
        ServiceDescriptor sDes = ServiceDescriptor.from(TestInterface.class, TestInterface.class.getDeclaredMethod("hello", String.class));
        Request request = new Request();
        request.setService(sDes);
        ServiceInstance sis = serviceManager.lookup(request);
        assertNotNull(sis);
        ReflectionUtils.invoke(sis.getTarget(), sis.getMethod(), "ZhiCheng");
    }
}