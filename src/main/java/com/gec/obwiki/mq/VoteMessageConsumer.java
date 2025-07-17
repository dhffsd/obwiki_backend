package com.gec.obwiki.mq;

import com.gec.obwiki.websocket.WebSocketServer;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "rocketmq.enabled", havingValue = "true", matchIfMissing = false)
@RocketMQMessageListener(topic = "VOTE_TOPIC", consumerGroup = "vote-consumer-group")
public class VoteMessageConsumer implements RocketMQListener<String> {
    @Autowired
    private WebSocketServer webSocketServer;

    @Override
    public void onMessage(String message) {
        // 实际应根据消息内容解析出目标用户token
        // 假设消息格式为：token:message
        String[] parts = message.split(":", 2);
        if (parts.length == 2) {
            webSocketServer.sendInfo(parts[0], parts[1]);
        }
    }
} 