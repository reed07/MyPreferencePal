package io.requery.cache;

import io.requery.EntityCache;
import io.requery.meta.Attribute;
import io.requery.meta.EntityModel;
import io.requery.meta.Type;
import io.requery.proxy.CompositeKey;
import io.requery.util.ClassMap;
import java.util.Map.Entry;
import java.util.Set;
import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.CacheManager;
import javax.cache.configuration.Factory;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.ExpiryPolicy;

public class SerializableEntityCache implements EntityCache {
    private final CacheManager cacheManager;
    private final ClassMap<Cache<?, ?>> caches;
    private final Factory<ExpiryPolicy> expiryPolicyFactory;
    private final EntityModel model;

    /* access modifiers changed from: protected */
    public String getCacheName(Type<?> type) {
        return type.getName();
    }

    /* access modifiers changed from: protected */
    public <K, V> void configure(MutableConfiguration<K, V> mutableConfiguration) {
        mutableConfiguration.setExpiryPolicyFactory(this.expiryPolicyFactory);
    }

    private <T> Class getKeyClass(Type<T> type) {
        Class cls;
        Set keyAttributes = type.getKeyAttributes();
        if (keyAttributes.isEmpty()) {
            return Integer.class;
        }
        if (keyAttributes.size() == 1) {
            Attribute attribute = (Attribute) keyAttributes.iterator().next();
            if (attribute.isAssociation()) {
                attribute = (Attribute) attribute.getReferencedAttribute().get();
            }
            cls = attribute.getClassType();
            if (cls.isPrimitive()) {
                if (cls == Integer.TYPE) {
                    cls = Integer.class;
                } else if (cls == Long.TYPE) {
                    cls = Long.class;
                }
            }
        } else {
            cls = CompositeKey.class;
        }
        return cls;
    }

    /* access modifiers changed from: protected */
    public <K, T> Cache<K, SerializedEntity<T>> createCache(String str, Type<T> type) {
        Class keyClass = getKeyClass(type);
        if (keyClass != null) {
            MutableConfiguration mutableConfiguration = new MutableConfiguration();
            mutableConfiguration.setTypes(keyClass, SerializedEntity.class);
            configure(mutableConfiguration);
            return this.cacheManager.createCache(str, mutableConfiguration);
        }
        throw new IllegalStateException();
    }

    private <K, T> Cache<K, SerializedEntity<T>> tryCreateCache(Class<T> cls) {
        Type typeOf = this.model.typeOf(cls);
        String cacheName = getCacheName(typeOf);
        Cache<K, SerializedEntity<T>> cache = this.cacheManager.getCache(cacheName);
        if (cache != null) {
            return cache;
        }
        try {
            return createCache(cacheName, typeOf);
        } catch (CacheException e) {
            Cache<K, SerializedEntity<T>> cache2 = this.cacheManager.getCache(cacheName);
            if (cache2 != null) {
                return cache2;
            }
            throw e;
        }
    }

    private Cache getCache(Class<?> cls) {
        Cache cache;
        synchronized (this.caches) {
            cache = (Cache) this.caches.get(cls);
            if (cache == null) {
                Type typeOf = this.model.typeOf(cls);
                cache = this.cacheManager.getCache(getCacheName(typeOf), getKeyClass(typeOf), SerializedEntity.class);
            }
        }
        return cache;
    }

    public <T> T get(Class<T> cls, Object obj) {
        Cache cache = getCache(cls);
        if (cache != null && cache.isClosed()) {
            cache = null;
        }
        if (cache != null) {
            SerializedEntity serializedEntity = (SerializedEntity) cache.get(obj);
            if (serializedEntity != null) {
                return cls.cast(serializedEntity.getEntity());
            }
        }
        return null;
    }

    public <T> void put(Class<T> cls, Object obj, T t) {
        Cache cache;
        synchronized (this.caches) {
            cache = getCache(cls);
            if (cache == null) {
                cache = tryCreateCache(cls);
            }
        }
        cache.put(obj, new SerializedEntity(cls, t));
    }

    public void invalidate(Class<?> cls) {
        Cache cache = getCache(cls);
        if (cache != null) {
            cache.clear();
            this.cacheManager.destroyCache(getCacheName(this.model.typeOf(cls)));
            synchronized (this.caches) {
                this.caches.remove(cls);
            }
            cache.close();
        }
    }

    public void invalidate(Class<?> cls, Object obj) {
        Cache cache = getCache(cls);
        if (cache != null && !cache.isClosed()) {
            cache.remove(obj);
        }
    }

    public void clear() {
        synchronized (this.caches) {
            for (Entry key : this.caches.entrySet()) {
                invalidate((Class) key.getKey());
            }
        }
    }
}
