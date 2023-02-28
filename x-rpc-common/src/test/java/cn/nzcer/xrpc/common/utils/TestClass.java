package cn.nzcer.xrpc.common.utils;

/**
 * @project: x-rpc
 * @ClassName: TestClass
 * @author: nzcer
 * @creat: 2023/2/28 22:33
 * @description:
 */
public class TestClass {
    private String privateMethod() {
        return "private";
    }
    public String publicMethod() {
        return "public";
    }
    protected String protectedMethod() {
        return "protected";
    }
}
