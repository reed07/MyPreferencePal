package com.brightcove.player.event;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.brightcove.player.util.ErrorUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventEmitterImpl implements EventEmitter {
    private static final String EVENT_KEY = "event";
    private static final String PROPERTY_PREFIX_KEY = "prop_";
    private String TAG;
    private List<RegistryEntry> entries;
    protected Handler handler;
    private boolean isEnabled;
    private Map<String, ArrayList<InvocationContainer>> listeners;

    class RegistryEntry {
        private EventEmitter eventEmitter;
        private EventListener eventListener;
        private String eventType;
        private int token = -1;

        public RegistryEntry(String str, EventListener eventListener2) {
            this.eventEmitter = EventEmitterImpl.this;
            this.eventType = str;
            this.eventListener = eventListener2;
        }

        public int getToken() {
            return this.token;
        }

        public void setToken(int i) {
            this.token = i;
        }

        public boolean matches(String str, EventListener eventListener2) {
            return this.eventEmitter == EventEmitterImpl.this && this.eventType.equals(str) && this.eventListener == eventListener2;
        }
    }

    @SuppressLint({"HandlerLeak"})
    public EventEmitterImpl() {
        this.TAG = "EventEmitterImpl";
        this.isEnabled = true;
        this.entries = new ArrayList();
        this.TAG = toString();
        this.handler = new Handler() {
            public void handleMessage(Message message) {
                Map map = (Map) message.obj;
                String str = (String) map.get("event");
                Event event = new Event(str);
                EventEmitterImpl.this.unpackProperties(map, event);
                EventEmitterImpl.this.invokeListenersForEventType(event, EventType.ANY);
                if (str.equals(EventType.RESPONSE)) {
                    EventEmitterImpl.this.invokeResponseListener(event);
                } else {
                    EventEmitterImpl.this.invokeListenersForEvent(event);
                }
            }
        };
        this.listeners = new HashMap();
    }

    private RegistryEntry getEntry(String str, EventListener eventListener) {
        for (RegistryEntry registryEntry : this.entries) {
            if (registryEntry.matches(str, eventListener)) {
                return registryEntry;
            }
        }
        return null;
    }

    public int on(String str, EventListener eventListener) {
        return on(str, eventListener, false);
    }

    private int on(String str, EventListener eventListener, boolean z) {
        if (!this.isEnabled) {
            return -1;
        }
        if (str == null || eventListener == null) {
            String str2 = this.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("Invalid input provided to on: evenType = ");
            sb.append(str);
            sb.append(", listener = ");
            sb.append(eventListener);
            Log.e(str2, sb.toString());
            throw new IllegalArgumentException(ErrorUtil.getMessage(ErrorUtil.EVENT_TYPE_AND_LISTENER_REQUIRED));
        }
        ArrayList invocations = getInvocations(str);
        InvocationContainer invocationContainer = new InvocationContainer(eventListener, z);
        if (invocationContainer.isDefault() || invocations.isEmpty() || !((InvocationContainer) invocations.get(invocations.size() - 1)).isDefault()) {
            invocations.add(invocationContainer);
        } else {
            int i = 0;
            int size = invocations.size();
            while (true) {
                if (i >= size) {
                    break;
                } else if (((InvocationContainer) invocations.get(i)).isDefault()) {
                    invocations.add(i, invocationContainer);
                    break;
                } else {
                    i++;
                }
            }
        }
        return invocationContainer.getToken();
    }

    public int once(String str, EventListener eventListener) {
        return on(str, eventListener, true);
    }

    public void off() {
        this.listeners.clear();
    }

    public void off(String str, int i) {
        if (i > -1) {
            ArrayList invocations = getInvocations(str);
            int invocationContainerPositionByToken = getInvocationContainerPositionByToken(invocations, i);
            if (invocationContainerPositionByToken > -1) {
                invocations.remove(invocationContainerPositionByToken);
                return;
            }
            return;
        }
        String str2 = this.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("Off attempted for invalid token of ");
        sb.append(i);
        Log.w(str2, sb.toString());
    }

    public void emit(String str) {
        emit(str, Collections.emptyMap());
    }

    public void emit(String str, Map<String, Object> map) {
        if (!this.isEnabled) {
            return;
        }
        if (str != null) {
            Message obtain = Message.obtain();
            HashMap hashMap = new HashMap();
            hashMap.put("event", str);
            if (!map.isEmpty()) {
                packProperties(map, hashMap);
            }
            obtain.obj = hashMap;
            this.handler.sendMessage(obtain);
            return;
        }
        Log.e(this.TAG, "Received an emit without an EventType");
        throw new IllegalArgumentException(ErrorUtil.getMessage(ErrorUtil.INVALID_EMIT));
    }

    public void request(String str, EventListener eventListener) {
        request(str, new HashMap(), eventListener);
    }

    public void request(String str, Map<String, Object> map, EventListener eventListener) {
        if (this.isEnabled) {
            int once = once(EventType.RESPONSE, eventListener);
            if (map == null) {
                map = new HashMap<>();
            }
            try {
                map.put(AbstractEvent.REQUEST_TOKEN, Integer.valueOf(once));
            } catch (UnsupportedOperationException unused) {
                HashMap hashMap = new HashMap(map);
                hashMap.put(AbstractEvent.REQUEST_TOKEN, Integer.valueOf(once));
                map = hashMap;
            }
            emit(str, map);
        }
    }

    public void respond(Map<String, Object> map) {
        if (!this.isEnabled) {
            return;
        }
        if (map.containsKey(AbstractEvent.REQUEST_TOKEN)) {
            emit(EventType.RESPONSE, map);
        } else {
            Log.d(this.TAG, "Respond attempted without an requestToken");
        }
    }

    public void respond(Event event) {
        respond(event.properties);
    }

    private void packProperties(Map<String, Object> map, Map<String, Object> map2) {
        for (String str : map.keySet()) {
            StringBuilder sb = new StringBuilder();
            sb.append(PROPERTY_PREFIX_KEY);
            sb.append(str);
            map2.put(sb.toString(), map.get(str));
        }
    }

    /* access modifiers changed from: private */
    public void unpackProperties(Map<String, Object> map, Event event) {
        for (String str : map.keySet()) {
            if (str.startsWith(PROPERTY_PREFIX_KEY)) {
                event.properties.put(str.substring(5), map.get(str));
            }
        }
    }

    private ArrayList<InvocationContainer> getInvocations(String str) {
        if (this.listeners.containsKey(str)) {
            return (ArrayList) this.listeners.get(str);
        }
        ArrayList arrayList = new ArrayList();
        this.listeners.put(str, arrayList);
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public int getInvocationContainerPositionByToken(List<InvocationContainer> list, int i) {
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (((InvocationContainer) list.get(i2)).getToken() == i) {
                return i2;
            }
        }
        return -1;
    }

    /* access modifiers changed from: protected */
    public InvocationContainer getInvocationContainerByToken(List<InvocationContainer> list, int i) {
        int invocationContainerPositionByToken = getInvocationContainerPositionByToken(list, i);
        if (invocationContainerPositionByToken >= 0) {
            return (InvocationContainer) list.get(invocationContainerPositionByToken);
        }
        return null;
    }

    /* access modifiers changed from: private */
    public void invokeResponseListener(Event event) {
        ArrayList invocations = getInvocations(event.getType());
        int integerProperty = event.getIntegerProperty(AbstractEvent.REQUEST_TOKEN);
        InvocationContainer invocationContainerByToken = getInvocationContainerByToken(invocations, integerProperty);
        if (invocationContainerByToken != null) {
            try {
                invocationContainerByToken.getListener().processEvent(event);
            } catch (Throwable th) {
                Log.e(this.TAG, "processEvent() threw a throwable.", th);
            }
            off(event.getType(), integerProperty);
        }
    }

    /* access modifiers changed from: private */
    public void invokeListenersForEvent(Event event) {
        invokeListenersForEventType(event, event.getType());
    }

    /* access modifiers changed from: private */
    public void invokeListenersForEventType(Event event, String str) {
        ArrayList invocations = getInvocations(str);
        for (InvocationContainer invocationContainer : (List) invocations.clone()) {
            if ((!invocationContainer.isDefault() && !event.isStopped()) || (invocationContainer.isDefault() && !event.isPrevented())) {
                try {
                    invocationContainer.getListener().processEvent(event);
                } catch (Throwable th) {
                    Log.e(this.TAG, "processEvent() threw a throwable.", th);
                }
                if (invocationContainer.shouldRemove()) {
                    invocations.remove(invocationContainer);
                }
            }
        }
    }

    public void enable() {
        this.isEnabled = true;
    }

    public void disable() {
        this.isEnabled = false;
    }
}
