package cn.lihuan.com.socketserver;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/websocket/{userId}")  // 这个注解的作用就是指定客户端请求的API
@Component
@Slf4j
public class MySocketServer {

    //用来存放每个客户端对应的 MySocketServer对象
    private static ConcurrentHashMap<String, MySocketServer> webClient = new ConcurrentHashMap<>();
    //与某个客户端连接会话，需要它来给客户端发送信息
    private Session session;
    //用户ID
    private String userId;

    //建立连接调用
    @OnOpen
    public void onOpen(@PathParam("userId") String userId, Session session) {
        this.userId = userId;
        this.session = session;

         if(webClient.containsKey(userId)){
             webClient.remove(userId);
             webClient.put(userId,this);
         } else{
             webClient.put(userId,this);
         }
         sendMessage("用户为"+this.userId+"连接成功！");
         log.info("用户ID为:{}已连接成功!}",userId);
    }
    //连接断开调用
    @OnClose
    public void onClose(){
        if (webClient.containsKey(userId)){
            webClient.remove(userId);
        }
        log.info("用户iD为:{},已断开连接",userId);
    }

    //收到客户端信息调用
    @OnMessage
    public void onMessage(String message,Session session){
        log.info("用户信息："+this.userId+ "报文数据："+message);
        if(!StringUtils.isEmpty(message)){
            JSONObject parse = JSON.parseObject(message);
            parse.put("fromUserid",this.userId);  //标注一下这是哪个客户端发送的信息
            String toUserId = parse.getString("toUserId");
            if(!StringUtils.isEmpty(toUserId) && webClient.containsKey(toUserId)){
                MySocketServer mySocketServer = webClient.get(toUserId);  //拿到这个客户端
                mySocketServer.sendMessage(parse.toJSONString());  //这个客户端发送信息
            }
        }
    }
    // 连接失败调用
    @OnError
    public void onError(Session session,Throwable throwable){
        log.error("用户错误："+this.userId+"错误信息是："+throwable.getMessage());
    }


    //服务器主动推送信息给客户端
    public void sendMessage(String message){
        try {
            this.session.getBasicRemote().sendText(message); //当前的MySocketServer对象，session就是当前用户的信息
        } catch (Exception exception){
            exception.printStackTrace();
       }
        log.info("服务端向，用户ID为：{},发送信息成功！",this.userId);
    }
}
