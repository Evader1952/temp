package com.mp.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsMessagingTemplate;

import javax.jms.ConnectionFactory;

/**
 * @desciption 消息中间件配置信息初始化
 * @author duchong
 * @date 2018-11-7 10:59:09
 * @version v1.0.0
 **/
@Configuration
@ConditionalOnProperty(value = "activemq.broker-url")
public class JmsMessagingConfig {

    @Value("${activemq.broker-url}")
    private String url;

    @Value("${activemq.user}")
    private String username;

    @Value("${activemq.password}")
    private String password;

    @Value("${activemq.enabled}")
    private Boolean enabled;

    @Value("${activemq.max-connections}")
    private Integer maxConnections;

    @Value("${activemq.expiry-timeout}")
    private Integer expiryTimeout;

    @Value("${activemq.idle-timeout}")
    private Integer idleTimeout;


    @Bean
    public ConnectionFactory connectionFactory(){
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
        factory.setBrokerURL(url);
        factory.setUserName(username);
        factory.setPassword(password);
        return factory;
    }

    @Bean
    public JmsMessagingTemplate jmsMessagingTemplate(){
        JmsMessagingTemplate template = new JmsMessagingTemplate(connectionFactory());
        return template;
    }

    @Bean(name = "sendMessage")
    public SendMessage sendMessage(){
        SendMessage message = new SendMessage();
        message.setJmsMessagingTemplate(jmsMessagingTemplate());
        return message;
    }
}