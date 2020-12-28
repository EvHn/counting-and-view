package example.view.services;

import example.view.data.CountingResult;
import example.view.jpa.CountingResultRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.mockito.Mockito.*;


class ViewServiceTest {

    @Mock
    private CountingResultRepository repository;
    @InjectMocks
    private ViewService msgService;

    @BeforeEach
    public void setUp() {
        msgService = new ViewService();
        msgService.init();
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void processMessage() {
        Date date = new Date(1000000L);
        String msg = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><countingResult " +
                "type=\"Cat\" count=\"13\" date=\"" + new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssXXX").format(date) + "\"/>";
        msgService.processMessage(msg);
        verify(repository).addCountingResult(new CountingResult(0, "Cat", 13, date));
    }
}