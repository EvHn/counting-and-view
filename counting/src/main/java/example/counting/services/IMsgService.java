package example.counting.services;

import example.counting.data.CountingResult;

public interface IMsgService {
    void sendCountingResult(CountingResult countingResult);
}
