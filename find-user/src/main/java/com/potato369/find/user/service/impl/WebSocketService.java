package com.potato369.find.user.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
@ServerEndpoint("/websocket/{userId}")
public class WebSocketService {

    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static ConcurrentHashMap<String, WebSocketService> webSocketServiceSet = new ConcurrentHashMap<>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    //当前发消息的人员编号
    private String userId = "";

    /**
     * <pre>
     * @param session
     * @Title: onOpen
     * @Description 连接建立成功调用的方法
     * </pre>
     */
    @OnOpen
    public void onOpen(@PathParam(value = "userId") String param, Session session, EndpointConfig config) {
        try {
            userId = param;
            this.session = session;
            //加入set中
            webSocketServiceSet.put(userId, this);
            //在线数加1
            addOnlineCount();
            if (log.isDebugEnabled()) {
                log.debug("【WebSocket消息】有新的连接开始监听：连接总数total={}, 当前在线人数count={}", webSocketServiceSet.size(), getOnlineCount());
            }
        } catch (Exception e) {
            log.error("【WebSocket消息】websocket异常", e);
        }
    }

    /**
     * <pre>
     * @Title onClose
     * </pre>
     */
    @OnClose
    public void onClose() {
        if (!userId.equals("")) {
            //从set中删除
            webSocketServiceSet.remove(userId);
            //在线数减1
            subOnlineCount();
            if (log.isDebugEnabled()) {
                log.debug("有连接关闭！当前在线人数为：{}", getOnlineCount());
            }
        }
    }

    /**
     * 给指定的人发送消息
     *
     * @param message
     */
    public void sendToUser(String message) {
        if (log.isDebugEnabled()) {
            log.debug("message={}", message);
        }
        String sendUserId = message.split("[|]")[1];
        String sendMessage = message.split("[|]")[0];
        String now = getNowTime();
        try {
            if (webSocketServiceSet.get(sendUserId) != null) {
                webSocketServiceSet.get(sendUserId).sendMessage(now + "用户" + userId + "发来消息：" + " <br/> " + sendMessage);
            } else {
                log.warn("当前用户不在线");
            }
        } catch (Exception e) {
            log.error("给指定的人发送消息出现错误", e);
        }
    }

    /**
     * 给所有人发消息
     *
     * @param message
     */
    public void sendAll(String message) {
        String now = getNowTime();
        String sendMessage = message.split("[|]")[0];
        //遍历HashMap
        for (String key : webSocketServiceSet.keySet()) {
            try {
                //判断接收用户是否是当前发消息的用户
                if (!userId.equals(key)) {
                    webSocketServiceSet.get(key).sendMessage(now + "用户" + userId + "发来消息：" + " <br/> " + sendMessage);
                    if (log.isDebugEnabled()) {
                        log.debug("key={}", key);
                    }
                }
            } catch (Exception e) {
                log.error("给所有人发消息出现错误", e);
            }
        }
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    private String getNowTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    /**
     * 发生错误时调用
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("websocket发生错误", error);
    }

    /**
     * 这个方法与上面几个方法不一样。
     * 没有用注解，是根据自己需要添加的方法。
     *
     * @param message
     * @throws Exception
     */
    public void sendMessage(String message) throws Exception {
        this.session.getBasicRemote().sendText(message);
    }

    public static synchronized Integer getOnlineCount() {
        return WebSocketService.onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketService.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketService.onlineCount--;
    }
}