package example.view.services;

import example.view.data.CountingResult;
import example.view.data.Order;

import java.util.List;

/**
 * @author evgkhan
 */
public interface IViewService {
    List<CountingResult> getCountingResults(Order order);

    void processMessage(String msg);
}
