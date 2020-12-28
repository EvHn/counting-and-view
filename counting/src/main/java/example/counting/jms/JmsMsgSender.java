package example.counting.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;

/**
 * Sends messages to the countingResultQueue
 * @author evgkhan
 */
public class JmsMsgSender implements IMsgSender {

    private static Logger logger = LoggerFactory.getLogger(JmsMsgSender.class);

    @Resource(mappedName = "jms/ConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(mappedName = "jms/countingResultQueue")
    private Queue queue;

    @Override
    public void sendMsg(String msg) {
        try (JMSContext context = connectionFactory.createContext()){
            context.createProducer().send(queue, msg);
            logger.info("Send message: {}", msg);
        } catch (Exception e) {
            logger.error("Error sending message: ", e);
        }
    }
}
