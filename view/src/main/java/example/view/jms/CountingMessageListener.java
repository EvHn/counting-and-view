package example.view.jms;

import example.view.services.IViewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * @author evgkhan
 */
@MessageDriven(mappedName = "jms/countingResultQueue")
public class CountingMessageListener implements MessageListener {

    @Inject
    private IViewService service;

    @Override
    public void onMessage(Message message) {
        try {
            service.processMessage(message.getBody(String.class));
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
