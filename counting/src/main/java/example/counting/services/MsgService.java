package example.counting.services;

import example.counting.data.CountingResult;
import example.counting.jms.IMsgSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

/**
 * Service for sending results
 * @author evgkhan
 */
public class MsgService implements IMsgService {

    private static Logger logger = LoggerFactory.getLogger(MsgService.class);

    private Marshaller marshaller;

    @Inject
    private IMsgSender sender;

    @PostConstruct
    public void init() {
        try {
            marshaller = JAXBContext.newInstance(CountingResult.class).createMarshaller();
        } catch (JAXBException e) {
            logger.error("Marshaller initialization error", e);
        }
    }

    @Override
    public void sendCountingResult(CountingResult countingResult) {
        StringWriter writer = new StringWriter();
        try {
            marshaller.marshal(countingResult, writer);
        } catch (JAXBException e) {
            logger.error("Conversion error", e);
            return;
        }
        sender.sendMsg(writer.toString());
    }
}
