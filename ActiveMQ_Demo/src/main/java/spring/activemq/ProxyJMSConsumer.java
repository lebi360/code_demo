package spring.activemq;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.listener.SessionAwareMessageListener;

import javax.jms.*;

/**
 * JMS消费者
 *
 *
 * <p>
 * 消息题的内容定义
 * <p>
 * 消息对象 接收消息对象后： 接收到的消息体* <p> 
 */
public class ProxyJMSConsumer implements SessionAwareMessageListener {

    public ProxyJMSConsumer() {

    }

    private JmsTemplate jmsTemplate;

    public JmsTemplate getJmsTemplate() {
        return jmsTemplate;
    }

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void onMessage(Message message, Session session) throws JMSException {
        System.out.println(message);
    }
}