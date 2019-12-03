package lab;

import org.ehcache.config.builders.CacheEventListenerConfigurationBuilder;
import org.ehcache.core.events.CacheEventListenerConfiguration;
import org.ehcache.event.EventType;
import org.ehcache.impl.config.event.DefaultCacheEventListenerConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.CacheEntryListenerConfiguration;
import javax.cache.configuration.FactoryBuilder;
import javax.cache.configuration.MutableCacheEntryListenerConfiguration;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.event.CacheEntryCreatedListener;
import javax.cache.event.CacheEntryEvent;
import javax.cache.event.CacheEntryListenerException;
import javax.cache.expiry.CreatedExpiryPolicy;
import javax.cache.expiry.Duration;
import javax.cache.spi.CachingProvider;
import java.util.Set;
import java.util.concurrent.TimeUnit;

//import javax.cache.

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public CacheManager cachManager() {
        // TODO: Specify ehcache as provider?
        CachingProvider cachingProvider = Caching.getCachingProvider();
        CacheManager cacheManager = cachingProvider.getCacheManager();
        MutableConfiguration<Object, Object> config = new MutableConfiguration<>();
        config.setExpiryPolicyFactory(CreatedExpiryPolicy.factoryOf(new Duration(TimeUnit.SECONDS, 5)));
        Cache<Object, Object> cache = cacheManager.createCache("timeCache", config);

//        MutableCacheEntryListenerConfiguration<Object, Object> mutableCacheEntryListenerConfiguration
//                = new MutableCacheEntryListenerConfiguration<Object, Object>();
//        mutableCacheEntryListenerConfiguration.setCacheEntryListenerFactory(FactoryBuilder.factoryOf(CacheEntryListener.class));

        // Listener
//        Class<? extends CacheEventListener<?, ?>> listner;
//        CacheEventListenerConfigurationBuilder cacheEventListenerConfiguration = CacheEventListenerConfigurationBuilder
//                .newEventListenerConfiguration(new MyCacheEventListener(), EventType.CREATED, EventType.EXPIRED);
//        cacheEventListenerConfiguration.unordered();
//        cacheEventListenerConfiguration.asynchronous();
//        cacheEventListenerConfiguration.build();

        // Compiles
//        Factory<CacheEntryListener> cacheEntryListenerFactory = FactoryBuilder.factoryOf(CacheEntryListener.class);
//        CacheEntryListenerConfiguration cacheEntryListenerConfiguration
//                = new MutableCacheEntryListenerConfiguration<>(cacheEntryListenerFactory, null, false, true);

//        cache.registerCacheEntryListener(cacheEventListenerConfiguration);
        return cacheManager;
    }


    class CacheEntryListener implements CacheEntryCreatedListener<Object, Object> {
        @Override
        public void onCreated(Iterable<CacheEntryEvent<?, ?>> iterable) throws CacheEntryListenerException {
            for (CacheEntryEvent<?, ?> cacheEntryEvent : iterable) {
                System.err.println(String.format("event=%s, key=%s", cacheEntryEvent.getEventType(), cacheEntryEvent.getKey()));
            }
        }
    }
}

