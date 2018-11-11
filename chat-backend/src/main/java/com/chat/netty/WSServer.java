package com.chat.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author yzj
 * @Date 2018/11/9 22:44
 * @Description 编写netty服务
 */
@Slf4j
@Component
public class WSServer {

    private static class SingletionWsServer {
        static final WSServer instance = new WSServer();


    }

    public static WSServer getInstance() {
        return SingletionWsServer.instance;
    }

    private NioEventLoopGroup mainGroup;
    private NioEventLoopGroup subGroup;
    private ServerBootstrap server;
    private ChannelFuture future;


    public WSServer() {
        //创建主链接
        mainGroup = new NioEventLoopGroup();
        subGroup = new NioEventLoopGroup();
        server = new ServerBootstrap();
        server.group(mainGroup, subGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChatHandler());
    }

    public void start() {
        future = server.bind(9999);
        log.info("netty websocket 启动啦.....");
    }

}
