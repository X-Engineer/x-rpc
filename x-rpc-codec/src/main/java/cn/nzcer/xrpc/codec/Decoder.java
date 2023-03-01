package cn.nzcer.xrpc.codec;

/**
 * @project: x-rpc
 * @ClassName: Decoder
 * @author: nzcer
 * @creat: 2023/3/1 20:09
 * @description:
 */
public interface Decoder {
    <T> T decode(byte[] bytes, Class<T> clazz);
}
