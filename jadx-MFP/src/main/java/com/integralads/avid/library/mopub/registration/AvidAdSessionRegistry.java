package com.integralads.avid.library.mopub.registration;

import android.view.View;
import com.integralads.avid.library.mopub.session.AbstractAvidAdSession;
import com.integralads.avid.library.mopub.session.internal.InternalAvidAdSession;
import com.integralads.avid.library.mopub.session.internal.InternalAvidAdSessionListener;
import java.util.Collection;
import java.util.HashMap;

public class AvidAdSessionRegistry implements InternalAvidAdSessionListener {
    private static AvidAdSessionRegistry instance = new AvidAdSessionRegistry();
    private int activeSessionCount = 0;
    private final HashMap<String, AbstractAvidAdSession> avidAdSessions = new HashMap<>();
    private final HashMap<String, InternalAvidAdSession> internalAvidAdSessions = new HashMap<>();
    private AvidAdSessionRegistryListener listener;

    public static AvidAdSessionRegistry getInstance() {
        return instance;
    }

    public AvidAdSessionRegistryListener getListener() {
        return this.listener;
    }

    public void setListener(AvidAdSessionRegistryListener avidAdSessionRegistryListener) {
        this.listener = avidAdSessionRegistryListener;
    }

    public void registerAvidAdSession(AbstractAvidAdSession abstractAvidAdSession, InternalAvidAdSession internalAvidAdSession) {
        this.avidAdSessions.put(abstractAvidAdSession.getAvidAdSessionId(), abstractAvidAdSession);
        this.internalAvidAdSessions.put(abstractAvidAdSession.getAvidAdSessionId(), internalAvidAdSession);
        internalAvidAdSession.setListener(this);
        if (this.avidAdSessions.size() == 1) {
            AvidAdSessionRegistryListener avidAdSessionRegistryListener = this.listener;
            if (avidAdSessionRegistryListener != null) {
                avidAdSessionRegistryListener.registryHasSessionsChanged(this);
            }
        }
    }

    public Collection<InternalAvidAdSession> getInternalAvidAdSessions() {
        return this.internalAvidAdSessions.values();
    }

    public Collection<AbstractAvidAdSession> getAvidAdSessions() {
        return this.avidAdSessions.values();
    }

    public boolean isEmpty() {
        return this.avidAdSessions.isEmpty();
    }

    public boolean hasActiveSessions() {
        return this.activeSessionCount > 0;
    }

    public AbstractAvidAdSession findAvidAdSessionById(String str) {
        return (AbstractAvidAdSession) this.avidAdSessions.get(str);
    }

    public InternalAvidAdSession findInternalAvidAdSessionById(String str) {
        return (InternalAvidAdSession) this.internalAvidAdSessions.get(str);
    }

    public InternalAvidAdSession findInternalAvidAdSessionByView(View view) {
        for (InternalAvidAdSession internalAvidAdSession : this.internalAvidAdSessions.values()) {
            if (internalAvidAdSession.doesManageView(view)) {
                return internalAvidAdSession;
            }
        }
        return null;
    }

    public void sessionDidEnd(InternalAvidAdSession internalAvidAdSession) {
        this.avidAdSessions.remove(internalAvidAdSession.getAvidAdSessionId());
        this.internalAvidAdSessions.remove(internalAvidAdSession.getAvidAdSessionId());
        internalAvidAdSession.setListener(null);
        if (this.avidAdSessions.size() == 0) {
            AvidAdSessionRegistryListener avidAdSessionRegistryListener = this.listener;
            if (avidAdSessionRegistryListener != null) {
                avidAdSessionRegistryListener.registryHasSessionsChanged(this);
            }
        }
    }

    public void sessionHasBecomeActive(InternalAvidAdSession internalAvidAdSession) {
        this.activeSessionCount++;
        if (this.activeSessionCount == 1) {
            AvidAdSessionRegistryListener avidAdSessionRegistryListener = this.listener;
            if (avidAdSessionRegistryListener != null) {
                avidAdSessionRegistryListener.registryHasActiveSessionsChanged(this);
            }
        }
    }

    public void sessionHasResignedActive(InternalAvidAdSession internalAvidAdSession) {
        this.activeSessionCount--;
        if (this.activeSessionCount == 0) {
            AvidAdSessionRegistryListener avidAdSessionRegistryListener = this.listener;
            if (avidAdSessionRegistryListener != null) {
                avidAdSessionRegistryListener.registryHasActiveSessionsChanged(this);
            }
        }
    }
}
