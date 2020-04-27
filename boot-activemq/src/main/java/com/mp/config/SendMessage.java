package com.mp.config;

import org.apache.activemq.ScheduledMessage;
import org.apache.activemq.command.ActiveMQQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsMessagingTemplate;

import javax.jms.*;

/**
 * @desciption 推送消息及延时消息
 * @author duchong
 * @date 2018-11-7 10:59:09
 * @version v1.0.0
 **/
public class SendMessage {

    private final static Logger log = LoggerFactory.getLogger(SendMessage.class);

    private static JmsMessagingTemplate jmsMessagingTemplate;

    public void setJmsMessagingTemplate(JmsMessagingTemplate template) {
        jmsMessagingTemplate = template;
    }

    /**
     * 发送消息
     * @param queue_name    队列名称
     * @param msg           消息内容
     */
    public static boolean sendMessage(String queue_name, String msg) {
        log.error("队列：" + queue_name + "，内容：" + msg);
        try {
            Destination destination = new ActiveMQQueue(queue_name);
            jmsMessagingTemplate.convertAndSend(destination, msg);
            return true;
        } catch (Exception e) {
            log.error("队列消息发送失败,队列名称:{},消息内容:{}", queue_name, msg);
            e.getMessage();
            return false;
        }
    }

    /**
     * 发送延时消息
     * @param queue_name    队列名称
     * @param msg           消息内容
     * @param time          延时时间（秒）
     */
    public static boolean sendDelayMessage(String queue_name, String msg, Long time) {
        //获取连接工厂
        ConnectionFactory connectionFactory = jmsMessagingTemplate.getConnectionFactory();
        try {
            log.error("队列：" + queue_name + "，内容：" + msg + "，延时：" + time);
            //获取连接
            Connection connection = connectionFactory.createConnection();
            connection.start();
            //获取session
            Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            // 创建一个消息队列
            Destination destination = session.createQueue(queue_name);
            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);
            TextMessage message = session.createTextMessage(msg);
            //设置延迟时间
            message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, time);
            //发送
            producer.send(message);
            session.commit();
            producer.close();
            session.close();
            connection.close();
            return true;
        } catch (Exception e) {
            log.error("队列消息发送失败,队列名称:{},消息内容:{},延时时间:{}", queue_name, msg, time);
            e.getMessage();
            return false;
        }
    }

}
