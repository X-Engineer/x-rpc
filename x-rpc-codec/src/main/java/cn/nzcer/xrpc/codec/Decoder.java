package cn.nzcer.xrpc.codec;

/**
 * @project: x-rpc
 * @ClassName: Decoder
 * @author: nzcer
 * @creat: 2023/3/1 20:09
 * @description:
 */
public interface Decoder {
    /**
     *
     * @param bytes 传入的字节流数据
     * @param clazz 反序列化后的类型
     * @return
     * @param <T>
     */
    <T> T decode(byte[] bytes, Class<T> clazz);
}
