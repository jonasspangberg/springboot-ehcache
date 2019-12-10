package lab;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TimeService {

    @Cacheable("cache")
    public LocalDateTime getTime(String key) {
        try {
            Thread.sleep(500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return LocalDateTime.now();
    }
}
