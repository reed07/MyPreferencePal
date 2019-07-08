package com.twitter.sdk.android.core;

import com.twitter.sdk.android.core.Session;
import com.twitter.sdk.android.core.internal.persistence.PreferenceStore;
import com.twitter.sdk.android.core.internal.persistence.PreferenceStoreStrategy;
import com.twitter.sdk.android.core.internal.persistence.SerializationStrategy;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

public class PersistedSessionManager<T extends Session> implements SessionManager<T> {
    private final AtomicReference<T> activeSessionRef;
    private final PreferenceStoreStrategy<T> activeSessionStorage;
    private final String prefKeySession;
    private final PreferenceStore preferenceStore;
    private volatile boolean restorePending;
    private final SerializationStrategy<T> serializer;
    private final ConcurrentHashMap<Long, T> sessionMap;
    private final ConcurrentHashMap<Long, PreferenceStoreStrategy<T>> storageMap;

    public PersistedSessionManager(PreferenceStore preferenceStore2, SerializationStrategy<T> serializationStrategy, String str, String str2) {
        this(preferenceStore2, serializationStrategy, new ConcurrentHashMap(1), new ConcurrentHashMap(1), new PreferenceStoreStrategy(preferenceStore2, serializationStrategy, str), str2);
    }

    PersistedSessionManager(PreferenceStore preferenceStore2, SerializationStrategy<T> serializationStrategy, ConcurrentHashMap<Long, T> concurrentHashMap, ConcurrentHashMap<Long, PreferenceStoreStrategy<T>> concurrentHashMap2, PreferenceStoreStrategy<T> preferenceStoreStrategy, String str) {
        this.restorePending = true;
        this.preferenceStore = preferenceStore2;
        this.serializer = serializationStrategy;
        this.sessionMap = concurrentHashMap;
        this.storageMap = concurrentHashMap2;
        this.activeSessionStorage = preferenceStoreStrategy;
        this.activeSessionRef = new AtomicReference<>();
        this.prefKeySession = str;
    }

    /* access modifiers changed from: 0000 */
    public void restoreAllSessionsIfNecessary() {
        if (this.restorePending) {
            restoreAllSessions();
        }
    }

    private synchronized void restoreAllSessions() {
        if (this.restorePending) {
            restoreActiveSession();
            restoreSessions();
            this.restorePending = false;
        }
    }

    private void restoreSessions() {
        for (Entry entry : this.preferenceStore.get().getAll().entrySet()) {
            if (isSessionPreferenceKey((String) entry.getKey())) {
                Session session = (Session) this.serializer.deserialize((String) entry.getValue());
                if (session != null) {
                    internalSetSession(session.getId(), session, false);
                }
            }
        }
    }

    private void restoreActiveSession() {
        Session session = (Session) this.activeSessionStorage.restore();
        if (session != null) {
            internalSetSession(session.getId(), session, false);
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean isSessionPreferenceKey(String str) {
        return str.startsWith(this.prefKeySession);
    }

    public T getActiveSession() {
        restoreAllSessionsIfNecessary();
        return (Session) this.activeSessionRef.get();
    }

    public void setActiveSession(T t) {
        if (t != null) {
            restoreAllSessionsIfNecessary();
            internalSetSession(t.getId(), t, true);
            return;
        }
        throw new IllegalArgumentException("Session must not be null!");
    }

    public T getSession(long j) {
        restoreAllSessionsIfNecessary();
        return (Session) this.sessionMap.get(Long.valueOf(j));
    }

    public Map<Long, T> getSessionMap() {
        restoreAllSessionsIfNecessary();
        return Collections.unmodifiableMap(this.sessionMap);
    }

    private void internalSetSession(long j, T t, boolean z) {
        this.sessionMap.put(Long.valueOf(j), t);
        PreferenceStoreStrategy preferenceStoreStrategy = (PreferenceStoreStrategy) this.storageMap.get(Long.valueOf(j));
        if (preferenceStoreStrategy == null) {
            preferenceStoreStrategy = new PreferenceStoreStrategy(this.preferenceStore, this.serializer, getPrefKey(j));
            this.storageMap.putIfAbsent(Long.valueOf(j), preferenceStoreStrategy);
        }
        preferenceStoreStrategy.save(t);
        Session session = (Session) this.activeSessionRef.get();
        if (session == null || session.getId() == j || z) {
            synchronized (this) {
                this.activeSessionRef.compareAndSet(session, t);
                this.activeSessionStorage.save(t);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public String getPrefKey(long j) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.prefKeySession);
        sb.append("_");
        sb.append(j);
        return sb.toString();
    }

    public void clearSession(long j) {
        restoreAllSessionsIfNecessary();
        if (this.activeSessionRef.get() != null && ((Session) this.activeSessionRef.get()).getId() == j) {
            synchronized (this) {
                this.activeSessionRef.set(null);
                this.activeSessionStorage.clear();
            }
        }
        this.sessionMap.remove(Long.valueOf(j));
        PreferenceStoreStrategy preferenceStoreStrategy = (PreferenceStoreStrategy) this.storageMap.remove(Long.valueOf(j));
        if (preferenceStoreStrategy != null) {
            preferenceStoreStrategy.clear();
        }
    }
}
