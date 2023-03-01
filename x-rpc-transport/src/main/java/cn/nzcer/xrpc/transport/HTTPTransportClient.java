package cn.nzcer.xrpc.transport;

import cn.nzcer.xrpc.proto.Peer;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @project: x-rpc
 * @ClassName: HTTPTransportClient
 * @author: nzcer
 * @creat: 2023/3/1 22:05
 * @description:
 */
public class HTTPTransportClient implements TransportClient {
    private String url;

    @Override
    public void connect(Peer peer) {
        this.url = "http://" + peer.getHost() + ":" + peer.getPort();
    }

    @Override
    public InputStream write(InputStream data) {
        try {
            HttpURLConnection httpConn = (HttpURLConnection) new URL(url).openConnection();
            httpConn.setDoOutput(true);
            httpConn.setDoInput(true);
            httpConn.setUseCaches(false);
            httpConn.setRequestMethod("POST");
            httpConn.connect();
            IOUtils.copy(data, httpConn.getOutputStream());
            int responseCode = httpConn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // 响应成功，拿到响应数据
                return httpConn.getInputStream();
            } else {
                // 响应不成功，拿到错误信息
                return httpConn.getErrorStream();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {

    }
}
