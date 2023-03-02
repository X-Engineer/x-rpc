package cn.nzcer.xrpc.client;

import cn.nzcer.xrpc.common.utils.ReflectionUtils;
import cn.nzcer.xrpc.proto.Peer;
import cn.nzcer.xrpc.transport.TransportClient;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @project: x-rpc
 * @ClassName: RandomTransportSelector
 * @author: nzcer
 * @creat: 2023/3/2 18:51
 * @description: 随机选择一个 client
 */
@Slf4j
public class RandomTransportSelector implements TransportSelector {
    /**
     * 已经连接好的 client
     */
    private List<TransportClient> clients;

    public RandomTransportSelector() {
        clients = new ArrayList<>();
    }

    @Override
    public synchronized void init(List<Peer> peers, int count, Class<? extends TransportClient> clazz) {
        count = Math.max(count, 1);
        for (Peer peer : peers) {
            for (int i = 0; i < count; i++) {
                TransportClient client = ReflectionUtils.newInstance(TransportClient.class);
                client.connect(peer);
                clients.add(client);
            }
            log.info("connect peer:{}", peer);
        }
    }

    @Override
    public synchronized TransportClient select() {
        // 随机从池子中选择一个
        int i = new Random().nextInt(clients.size());
        return clients.remove(i);
    }

    @Override
    public synchronized void release(TransportClient client) {
        clients.add(client);
    }

    @Override
    public synchronized void close() {
        for (TransportClient client : clients) {
            client.close();
        }
        clients.clear();
    }
}
