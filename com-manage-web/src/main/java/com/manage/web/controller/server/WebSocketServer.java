package com.manage.web.controller.server;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.manage.common.core.utils.SecurityUtils;
import com.manage.common.core.utils.StringUtils;
import com.manage.domain.service.ITShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@ServerEndpoint("/p/cs/wsServer")
public class WebSocketServer {

    @Autowired
    private static ITShopService shopService;

    static Log log = LogFactory.getLog(WebSocketServer.class);

    /**
     * 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
     */
    private static volatile int onlineCount = 0;

    /**
     * concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
     */
    private static ConcurrentHashMap<Long, WebSocketServer> webSocketMap = new ConcurrentHashMap<>();

    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;

    /**
     * 接收门店
     */
    private Long shopId = null;

    private String shopName;

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        this.shopId = SecurityUtils.getShopId();
        this.shopName = shopService.selectById(shopId).getShopName();
        if (webSocketMap.containsKey(shopId)) {
            webSocketMap.remove(shopId);
            webSocketMap.put(shopId, this);
            //加入set中
        } else {
            webSocketMap.put(shopId, this);
            //加入set中
            addOnlineCount();
            //在线数加1
        }

        log.info("门店连接:" + shopId + ",当前在线人数为:" + getOnlineCount());
        try {
            sendMessage("连接成功");
        } catch (IOException e) {
            log.error("门店:" + shopId + ",网络异常!!!!!!");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        if (webSocketMap.containsKey(shopId)) {
            webSocketMap.remove(shopId);
            //从set中删除
            subOnlineCount();
        }
        log.info("用户退出:" + shopName + ",当前在线人数为:" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("门店消息:" + shopName + ",报文:" + message);
        //可以群发消息
        //消息保存到数据库、redis
        if (StringUtils.isNotBlank(message)) {
            try {
                //解析发送的报文
                JSONObject jsonObject = JSON.parseObject(message);
                //追加发送人(防止串改)
                jsonObject.put("fromShopId", this.shopId);
                String toShopId = jsonObject.getString("toShopId");
                //传送给对应toUserId用户的websocket
                if (StringUtils.isNotBlank(toShopId) && webSocketMap.containsKey(toShopId)) {
                    webSocketMap.get(toShopId).sendMessage(jsonObject.toJSONString());
                } else {
                    log.error("请求的shopId:" + toShopId + "不在该服务器上");
                    //否则不在这个服务器上，发送到mysql或者redis
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("门店错误:" + this.shopId + ",原因:" + error.getMessage());
        error.printStackTrace();
    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    /**
     * 发送自定义消息
     */
    public static void sendInfo(String message, @PathParam("shopId") Long shopId) throws IOException {
        String shopName = shopService.selectById(shopId).getShopName();
        log.info("发送消息到:" + shopName + "，报文:" + message);
        if (shopId != null && webSocketMap.containsKey(shopId)) {
            webSocketMap.get(shopId).sendMessage(message);
        } else {
            log.error("用户" + shopName + ",不在线！");
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }
}
