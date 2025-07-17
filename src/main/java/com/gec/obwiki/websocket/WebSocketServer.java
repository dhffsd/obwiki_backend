package com.gec.obwiki.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint("/ws/{token}")
public class WebSocketServer {
    private static final Logger LOG = LoggerFactory.getLogger(WebSocketServer.class);
    private static final Map<String, Session> SESSIONS = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("token") String token) {
        SESSIONS.put(token, session);
        LOG.info("WebSocket连接建立, token: {}, 当前连接数: {}", token, SESSIONS.size());
    }

    @OnClose
    public void onClose(@PathParam("token") String token) {
        SESSIONS.remove(token);
        LOG.info("WebSocket连接关闭, token: {}, 当前连接数: {}", token, SESSIONS.size());
    }

    @OnMessage
    public void onMessage(String message, @PathParam("token") String token) {
        LOG.info("收到来自{}的消息: {}", token, message);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        LOG.error("WebSocket发生错误", error);
    }

    public void sendInfo(String token, String message) {
        Session session = SESSIONS.get(token);
        if (session != null && session.isOpen()) {
            try {
                session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                LOG.error("发送WebSocket消息失败", e);
            }
        }
    }

    public void sendMessageToAll(String message) {
        SESSIONS.values().forEach(session -> {
            if (session.isOpen()) {
                try {
                    session.getBasicRemote().sendText(message);
                } catch (IOException e) {
                    LOG.error("发送WebSocket消息失败", e);
                }
            }
        });
    }
} 