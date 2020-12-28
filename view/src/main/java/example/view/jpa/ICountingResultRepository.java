package example.view.jpa;

import example.view.data.CountingResult;
import example.view.data.Order;

import java.util.List;

/**
 * @author evgkhan
 */
public interface ICountingResultRepository {
    void addCountingResult(CountingResult countingResult);

    List<CountingResult> loadCountingResultsOrderBy(Order order, String field);
}
