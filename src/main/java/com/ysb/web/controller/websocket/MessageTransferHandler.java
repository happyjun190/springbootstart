package com.ysb.web.controller.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ysb.constant.RedisConstants;
import com.ysb.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wushenjun on 2017/7/14.
 */
@Component
public class MessageTransferHandler implements WebSocketHandler {

    private static final Logger logger = LoggerFactory.getLogger(MessageTransferHandler.class);
    private final ObjectMapper jsonMapper = new ObjectMapper();

    @Autowired
    RedisTemplate<String, String> redisTemplate;


    //初始化连接信息Map
    public static final Map<String, WebSocketSession> userIdToSocketSessionMap;
    public static final Map<WebSocketSession, String> socketSessionToUserIdMap;

    static {
        userIdToSocketSessionMap = new HashMap<>();
        socketSessionToUserIdMap = new HashMap<>();
    }

    /**
     * 建立连接后
     * @param session
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String uri = session.getUri().toString();
        String[] uriParams = uri.split("\\?")[1].split("=");
        //判断初始化是否有带有token,没带token则关掉连接
        if(uriParams==null||uriParams.length<2) {
            session.close();
        }
        String userToken = uriParams[1];
        String userTokenKey = RedisConstants.Prefix.ADMIN_TOKEN + userToken;
        String userIdStr = redisTemplate.opsForValue().get(userTokenKey);
        //token过期/无效情况
        if(StringUtil.isBlank(userIdStr)) {
            session.sendMessage(new TextMessage("登录失败,请重新登录"));
            session.close();
        }
        logger.info("连接成功......,userIdStr:{}", userIdStr);
        if(userIdToSocketSessionMap.get(userIdStr)!=null) {
            //TODO 需要加安全踢除的处理，先发消息再剔除连接
            //互踢 删除原有session
            WebSocketSession oldSession = userIdToSocketSessionMap.get(userIdStr);
            oldSession.sendMessage(new TextMessage(new Date() + ":在别的地方登录"));
            //发出互踢消息
            removeCloseSession(oldSession);
        }
        userIdToSocketSessionMap.put(userIdStr, session);
        socketSessionToUserIdMap.put(session, userIdStr);
    }

    /**
     * 消息处理，在客户端通过Websocket API发送的消息会经过这里，然后进行相应的处理
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        String userIdStr = socketSessionToUserIdMap.get(session);
        logger.info("handleMessage......,userIdStr:{}, message:{}", userIdStr, jsonMapper.writeValueAsString(message.getPayload()));
        session.sendMessage(new TextMessage(new Date() + ":心跳消息"));
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable throwable) throws Exception {
        session.sendMessage(new TextMessage(new Date() + ":发生异常,关闭连接"));
        removeCloseSession(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        removeCloseSession(session);
        logger.info("afterConnectionClosed......, closeStatusCode:{}", closeStatus.getCode());
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }


    /**
     * 发送指定消息给用户(true 发送成功 false 发送失败)
     * @param userId
     * @param message
     */
    public static boolean sendMessageToClient(String userId, String message) throws Exception {
        WebSocketSession socketSession = userIdToSocketSessionMap.get(userId);
        //会话不存在或者会话为非开启状态
        if(socketSession==null||!socketSession.isOpen()) {
            return false;
        }
        socketSession.sendMessage(new TextMessage(message));
        return true;
    }


    /**
     * 移除并关闭session
     * @param session
     * @throws IOException
     */
    private void removeCloseSession(WebSocketSession session) throws IOException {
        if(session==null) {
            logger.info("null session. need not close anymore");
            return;
        }
        // 从各种记录集合中移除用户与session
        String userIdStr = socketSessionToUserIdMap.get(session);
        userIdToSocketSessionMap.remove(userIdStr);
        socketSessionToUserIdMap.remove(session);
        //将会话关闭
        if(session.isOpen()) {
            session.close();
        }
    }
}
