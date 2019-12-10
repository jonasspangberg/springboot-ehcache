package lab;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.cache.configuration.FactoryBuilder;
import javax.cache.configuration.MutableCacheEntryListenerConfiguration;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.event.CacheEntryCreatedListener;
import javax.cache.event.CacheEntryEvent;
import javax.cache.event.CacheEntryListenerException;
import javax.cache.expiry.CreatedExpiryPolicy;
import javax.cache.expiry.Duration;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cacheManager -> {
            MutableConfiguration<Object, Object> config = new MutableConfiguration<>();
            config.setExpiryPolicyFactory(CreatedExpiryPolicy.factoryOf(new Duration(TimeUnit.SECONDS, 1)));
            config.addCacheEntryListenerConfiguration(
                    new MutableCacheEntryListenerConfiguration<>(FactoryBuilder.factoryOf(CacheEntryListener.class), null, false, true)
            );
            cacheManager.createCache("cache", config);
        };
    }

    public static class CacheEntryListener implements CacheEntryCreatedListener<Object, Object> {
        @Override
        public void onCreated(Iterable<CacheEntryEvent<?, ?>> iterable) throws CacheEntryListenerException {
            for (CacheEntryEvent<?, ?> cacheEntryEvent : iterable) {
                System.out.println(String.format("event=%s, key=%s", cacheEntryEvent.getEventType(), cacheEntryEvent.getKey()));
            }
        }
    }
}

