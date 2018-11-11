package com.chat;

import com.chat.netty.WSServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @Author yzj
 * @Date 2018/11/9 23:08
 * @Description TODO
 */
@Slf4j
@Component
public class NettyBooter implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext() == null)
            log.info("netty 启动了....");
            WSServer.getInstance().start();
    }
}
