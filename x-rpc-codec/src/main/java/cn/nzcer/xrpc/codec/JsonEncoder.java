package cn.nzcer.xrpc.codec;

import com.alibaba.fastjson2.JSON;

/**
 * @project: x-rpc
 * @ClassName: JsonEncoder
 * @author: nzcer
 * @creat: 2023/3/1 20:24
 * @description: 基于 json 的序列化实现
 */
public class JsonEncoder implements Encoder{
    @Override
    public byte[] encode(Object obj) {
        return JSON.toJSONBytes(obj);
    }
}
