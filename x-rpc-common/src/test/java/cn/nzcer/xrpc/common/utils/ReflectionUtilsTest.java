package cn.nzcer.xrpc.common.utils;

import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @project: x-rpc
 * @ClassName: ReflectionUtilsTest
 * @author: nzcer
 * @creat: 2023/2/28 22:32
 * @description:
 */
public class ReflectionUtilsTest {

    @Test
    public void testNewInstance() {
        TestClass testClass = ReflectionUtils.newInstance(TestClass.class);
        assertNotNull(testClass);
    }

    @Test
    public void testGetPublicMethods() {
        Method[] methods = ReflectionUtils.getPublicMethods(TestClass.class);
        assertEquals(1, methods.length);
        assertEquals("publicMethod", methods[0].getName());
    }

    @Test
    public void testInvoke() {
        Method[] methods = ReflectionUtils.getPublicMethods(TestClass.class);
        Method publicMethod = methods[0];

        TestClass t = new TestClass();
        Object r = ReflectionUtils.invoke(t, publicMethod);
        assertEquals("public", r);
    }
}