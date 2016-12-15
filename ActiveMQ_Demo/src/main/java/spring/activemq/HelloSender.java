import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.*;

public class HelloSender {

    /**
     * @param args
     */
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
                new String[] { "classpath:applicationContext.xml" });

        JmsTemplate template = (JmsTemplate) applicationContext
                .getBean("jmsTemplate");

        Destination destination = (Destination) applicationContext
                .getBean("destination");

        template.send(destination, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session
                        .createTextMessage("发送消息：Hello ActiveMQ Text Message！");
            }
        });
        System.out.println("成功发送了一条JMS消息");

    }

}