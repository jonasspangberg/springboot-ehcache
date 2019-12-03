package lab;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.cache.annotation.CacheResult;
import java.time.LocalDateTime;

@Service
public class TimeService {

    @Cacheable("timeCache")
    public LocalDateTime getTime(String param1, String param2) {
        try {
            Thread.sleep(500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return LocalDateTime.now();
    }
}
