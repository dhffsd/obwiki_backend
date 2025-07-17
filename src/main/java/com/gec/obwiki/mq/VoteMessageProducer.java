package com.gec.obwiki.mq;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "rocketmq.enabled", havingValue = "true", matchIfMissing = false)
public class VoteMessageProducer {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public void sendVoteMessage(Long docId, String docName) {
        String message = "文档【" + docName + "】被点赞！";
        rocketMQTemplate.convertAndSend("VOTE_TOPIC", message);
    }
} 