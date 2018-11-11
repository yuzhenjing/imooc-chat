package com.chat.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @Author yzj
 * @Date 2018/11/9 23:12
 * @Description 自定义
 */
public class WSServerInitialzer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel channel) throws Exception {

        ChannelPipeline pipeline = channel.pipeline();
        //websocker 基于http协议 所以要有http编码器
        pipeline.addLast(new HttpServerCodec());
        //对大数据流的支持
        pipeline.addLast(new ChunkedWriteHandler());
        //对httpmessage 进行聚合 聚合成FullHttpRequest 或者FullHttpResponse
        //几乎在netty中的鞭笞都会用到此handler
        pipeline.addLast(new HttpObjectAggregator(1024 * 64));
        /**************************以上是用于支持http协议****************/

        /**************************以下是支持httpWebsocket**************/

        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
        //自定义handler
        pipeline.addLast(new ChatHandler());

    }
}
