package cn.nzcer.example;

import cn.nzcer.xrpc.client.RpcClient;

/**
 * @project: x-rpc
 * @ClassName: Client
 * @author: nzcer
 * @creat: 2023/3/3 11:46
 * @description:
 */
public class Client {
    public static void main(String[] args) {
        RpcClient client = new RpcClient();
        CalcService service = client.getProxy(CalcService.class);
        int r1 = service.add(1, 2);
        int r2 = service.minus(10, 8);
        System.out.println(r1);
        System.out.println(r2);
    }
}
