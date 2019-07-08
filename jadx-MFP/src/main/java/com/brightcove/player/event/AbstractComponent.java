package com.brightcove.player.event;

import com.brightcove.player.util.ErrorUtil;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractComponent implements Component {
    /* access modifiers changed from: protected */
    public EventEmitter eventEmitter;
    protected Map<String, Integer> listenerTokens;

    public AbstractComponent(EventEmitter eventEmitter2, Class<? extends Component> cls) {
        this.listenerTokens = new HashMap();
        if (eventEmitter2 != null) {
            if (cls != null) {
                eventEmitter2 = RegisteringEventEmitter.build(eventEmitter2, cls);
            }
            this.eventEmitter = eventEmitter2;
            return;
        }
        throw new IllegalArgumentException(ErrorUtil.getMessage(ErrorUtil.EVENT_EMITTER_REQUIRED));
    }

    public AbstractComponent(EventEmitter eventEmitter2) {
        this(eventEmitter2, null);
    }

    public void addListener(String str, EventListener eventListener) {
        this.listenerTokens.put(str, Integer.valueOf(this.eventEmitter.on(str, eventListener)));
    }

    public void addOnceListener(String str, EventListener eventListener) {
        this.listenerTokens.put(str, Integer.valueOf(this.eventEmitter.once(str, eventListener)));
    }

    public void removeListener(String str) {
        if (this.listenerTokens.containsKey(str)) {
            this.eventEmitter.off(str, ((Integer) this.listenerTokens.get(str)).intValue());
        }
    }

    public void removeListeners() {
        for (String str : this.listenerTokens.keySet()) {
            this.eventEmitter.off(str, ((Integer) this.listenerTokens.get(str)).intValue());
        }
        this.listenerTokens.clear();
    }

    public EventEmitter getEventEmitter() {
        return this.eventEmitter;
    }
}
