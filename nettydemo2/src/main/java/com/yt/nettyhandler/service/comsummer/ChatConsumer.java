package com.yt.nettyhandler.service.comsummer;

import com.yt.nettyhandler.message.AbstractMessage;
import com.yt.nettyhandler.message.Message;
import com.yt.nettyhandler.service.router.CommandRouter;
import io.netty.channel.Channel;

/**
 * 聊天相关消息处理
 * @author: 杨涛
 * @date: 2021/11/25/025
 */
public class ChatConsumer implements NetworkConsumer{
    /**
     *命令路由
     */
    private CommandRouter router;
    /**
     * 根据对应的ProcessorId() 选择对应的processor进行消费 根据message 中的 用户id 找到对应的角色 ，根据message 显示对应的话。
     * @param channel 和 client i/o 操作组件
     * @param message
     */
    @Override
    public void consumer(Channel channel, Message message) {
        AbstractMessage abstractMessage = (AbstractMessage) message;
        //选择对应的路由处理
        this.router.dispatch(abstractMessage.getId(), message);
    }
}
