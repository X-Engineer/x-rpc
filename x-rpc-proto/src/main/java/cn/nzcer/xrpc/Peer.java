package cn.nzcer.xrpc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @project: x-rpc
 * @ClassName: Peer
 * @author: nzcer
 * @creat: 2023/2/28 21:10
 * @description: 表示网络传输的一个端点
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Peer {
    private String host;
    private int port;
}
