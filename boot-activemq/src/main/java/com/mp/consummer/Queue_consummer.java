package com.mp.consummer;

import com.alibaba.fastjson.JSONObject;
import com.mp.config.JmsMessaging;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Queue_consummer {
    @JmsListener(destination = JmsMessaging.ORDER_REDPACKSTATE_MESSAGE)     // 注解监听
    public void receive(String message) throws  Exception{
        JSONObject object = JSONObject.parseObject(message);
        System.out.println(" ***  消费者收到消息  ***"+object);
    }
}
