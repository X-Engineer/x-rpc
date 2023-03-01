package cn.nzcer.xrpc.common.utils;

import junit.framework.TestCase;

import java.lang.reflect.Method;

/**
 * @project: x-rpc
 * @ClassName: ReflectionUtilsTest
 * @author: nzcer
 * @creat: 2023/2/28 22:32
 * @description:
 */
public class ReflectionUtilsTest extends TestCase {

    public void testNewInstance() {
        TestClass testClass = ReflectionUtils.newInstance(TestClass.class);
        assertNotNull(testClass);
    }

    public void testGetPublicMethods() {
        Method[] methods = ReflectionUtils.getPublicMethods(TestClass.class);
        assertEquals(1, methods.length);
        assertEquals("publicMethod", methods[0].getName());
    }

    public void testInvoke() {
        Method[] methods = ReflectionUtils.getPublicMethods(TestClass.class);
        Method publicMethod = methods[0];

        TestClass t = new TestClass();
        Object r = ReflectionUtils.invoke(t, publicMethod);
        assertEquals("public", r);
    }
}