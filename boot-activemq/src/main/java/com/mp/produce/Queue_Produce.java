package com.mp.produce;

import com.alibaba.fastjson.JSONObject;
import com.mp.config.JmsMessaging;
import com.mp.config.SendMessage;
import org.springframework.stereotype.Component;

@Component
public class Queue_Produce {
//    @Autowired
//    private JmsMessagingTemplate jmsMessagingTemplate;
//
//    @Autowired
//    private Queue queue;
//
//    // 调用一次一个信息发出
//    public void produceMessage(){
//        jmsMessagingTemplate.convertAndSend(queue,"****"+ UUID.randomUUID().toString().substring(0,6));
//        System.err.println("生产者发出消息");
//    }
//
//    // 带定时投递的业务方法
//    @Scheduled(fixedDelay = 3000)    // 每3秒自动调用
//    public void produceMessageScheduled(){
//        jmsMessagingTemplate.convertAndSend(queue,"** scheduled **"+ UUID.randomUUID().toString().substring(0,6));
//        System.out.println("  produceMessage  send   ok   ");
//    }

    // 调用一次一个信息发出
    public void produceMessage(){
       // jmsMessagingTemplate.convertAndSend(queue,"****"+ UUID.randomUUID().toString().substring(0,6));

        JSONObject object = new JSONObject();
        object.put("sellerNo","1000000000");
        object.put("redPackType",0);
        SendMessage.sendMessage(JmsMessaging.ORDER_REDPACKSTATE_MESSAGE, object.toJSONString());
        System.err.println("生产者发出消息");
    }
}
