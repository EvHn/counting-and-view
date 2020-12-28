package example.view.services;

import example.view.data.CountingResult;
import example.view.data.Order;
import example.view.jpa.ICountingResultRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.List;

/**
 * @author evgkhan
 */
public class ViewService implements IViewService {
    Logger logger = LoggerFactory.getLogger(ViewService.class);

    @Inject
    private ICountingResultRepository repository;

    private Unmarshaller unmarshaller;

    @PostConstruct
    public void init() {
        try {
            unmarshaller = JAXBContext.newInstance(CountingResult.class).createUnmarshaller();
        } catch (JAXBException e) {
            logger.error("Unmarshaller initialization error", e);
        }
    }

    @Override
    public List<CountingResult> getCountingResults(Order order) {
        return repository.loadCountingResultsOrderBy(order, CountingResult.DATE);
    }

    @Override
    public void processMessage(String msg) {
        try {
            CountingResult result = (CountingResult) unmarshaller
                    .unmarshal(new StringReader(msg));
            repository.addCountingResult(result);
        } catch (JAXBException e) {
            logger.error("Conversion error", e);
        }
    }
}
