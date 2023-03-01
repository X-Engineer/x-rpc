package cn.nzcer.xrpc.codec;

import com.alibaba.fastjson.JSONObject;

/**
 * @project: x-rpc
 * @ClassName: JsonDecoder
 * @author: nzcer
 * @creat: 2023/3/1 21:14
 * @description: 基于 json 的反序列化
 */
public class JsonDecoder implements Decoder {

    @Override
    public <T> T decode(byte[] bytes, Class<T> clazz) {
        return JSONObject.parseObject(bytes, clazz);
    }
}
