package cn.nzcer.xrpc.codec;

/**
 * @project: x-rpc
 * @ClassName: Encoder
 * @author: nzcer
 * @creat: 2023/3/1 20:08
 * @description:
 */
public interface Encoder {
    /**
     * 将 obj 被序列化的对象
     * @param obj
     * @return
     */
    byte[] encode(Object obj);
}
