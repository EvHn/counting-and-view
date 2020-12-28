package example.counting.services;

import example.counting.data.CountingResult;
import example.counting.data.Type;
import example.counting.jms.IMsgSender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.xml.bind.JAXBException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static org.mockito.Mockito.*;

class MsgServiceTest {

    @Mock
    private IMsgSender msgSender;
    @InjectMocks
    private MsgService msgService;

    @BeforeEach
    public void setUp() {
        msgService = new MsgService();
        msgService.init();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void sendCountingResult() throws JAXBException {
        Date date = new Date(1000000L);
        msgService.sendCountingResult(new CountingResult(Type.Cat, 13, date));
        String msg = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><countingResult " +
                "type=\"Cat\" count=\"13\" date=\"" + new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssXXX").format(date) + "\"/>";
        verify(msgSender, atLeastOnce()).sendMsg(msg);
    }
}