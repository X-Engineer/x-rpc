package cn.nzcer.xrpc.server;

import lombok.extern.slf4j.Slf4j;

/**
 * @project: x-rpc
 * @ClassName: TestClass
 * @author: nzcer
 * @creat: 2023/3/2 14:07
 * @description:
 */
@Slf4j
public class TestClass implements TestInterface {

    @Override
    public void hello(String name) {
        log.info("Hello, {}", name);
    }
}
