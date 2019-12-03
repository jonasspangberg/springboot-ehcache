package lab;

import javax.cache.event.CacheEntryCreatedListener;
import javax.cache.event.CacheEntryEvent;
import javax.cache.event.CacheEntryListenerException;


public class CacheEnrtyListener implements CacheEntryCreatedListener<Object, Object> {
    @Override
    public void onCreated(Iterable<CacheEntryEvent<?, ?>> iterable) throws CacheEntryListenerException {
        for (CacheEntryEvent<?, ?> cacheEntryEvent : iterable) {
            System.err.println(cacheEntryEvent.getEventType() + " " + cacheEntryEvent.getKey());
        }
    }
}
