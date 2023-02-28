package cn.nzcer.xrpc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @project: x-rpc
 * @ClassName: Request
 * @author: nzcer
 * @creat: 2023/2/28 21:17
 * @description:表示 RPC 的一个请求
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Request {
    private ServiceDescriptor service;
    private Object[] parameters;

}
