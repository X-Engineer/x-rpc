package cn.nzcer.xrpc.proto;

import lombok.Data;

/**
 * @project: x-rpc
 * @ClassName: Response
 * @author: nzcer
 * @creat: 2023/2/28 21:18
 * @description: 表示 RPC 的返回
 */
@Data
public class Response {
    /**
     * 服务返回编码 0-成功 非0-失败
     */
    private int statusCode = 0;

    /**
     * 具体的错误信息
     */
    private String statusMessage = "success";

    /**
     * 返回的数据
     */
    private Object data;
}
