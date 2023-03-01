package cn.nzcer.xrpc.codec;

import junit.framework.TestCase;

/**
 * @project: x-rpc
 * @ClassName: JsonEncoderTest
 * @author: nzcer
 * @creat: 2023/3/1 21:21
 * @description:
 */
public class JsonEncoderTest extends TestCase {

    public void testEncode() {
        JsonEncoder je = new JsonEncoder();
        TestBean bean = new TestBean();
        bean.setName("nzcer");
        bean.setAge(23);
        byte[] bytes = je.encode(bean);
        assertNotNull(bytes);
    }
}