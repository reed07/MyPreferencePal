package com.brightcove.player.event;

import android.util.Log;
import com.brightcove.player.util.ErrorUtil;
import java.util.concurrent.atomic.AtomicInteger;

class InvocationContainer {
    private static String PROCESS_EVENT_METHOD_NAME = "processEvent";
    private static final String TAG = "com.brightcove.player.event.InvocationContainer";
    private static AtomicInteger count;
    private int id;
    private boolean isDefault;
    private EventListener listener;
    private boolean shouldRemove;

    public InvocationContainer(EventListener eventListener, boolean z) {
        if (eventListener != null) {
            if (count == null) {
                count = new AtomicInteger();
            }
            this.id = count.incrementAndGet();
            this.listener = eventListener;
            this.isDefault = isDefaultListener(eventListener);
            this.shouldRemove = z;
            return;
        }
        throw new IllegalArgumentException(ErrorUtil.getMessage(ErrorUtil.LISTENER_REQUIRED));
    }

    private static boolean isDefaultListener(EventListener eventListener) {
        if (eventListener != null) {
            try {
                return eventListener.getClass().getMethod(PROCESS_EVENT_METHOD_NAME, new Class[]{Event.class}).isAnnotationPresent(Default.class);
            } catch (NoSuchMethodException unused) {
                String str = TAG;
                StringBuilder sb = new StringBuilder();
                sb.append(PROCESS_EVENT_METHOD_NAME);
                sb.append(" method seems to be missing from this handler!");
                Log.e(str, sb.toString());
                return false;
            }
        } else {
            throw new IllegalArgumentException(ErrorUtil.getMessage(ErrorUtil.LISTENER_REQUIRED));
        }
    }

    public int getToken() {
        return this.id;
    }

    public EventListener getListener() {
        return this.listener;
    }

    public boolean isDefault() {
        return this.isDefault;
    }

    public int getTotalCount() {
        return count.get();
    }

    public boolean shouldRemove() {
        return this.shouldRemove;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("InvocationContainer (");
        sb.append(this.id);
        sb.append(")");
        return sb.toString();
    }
}
