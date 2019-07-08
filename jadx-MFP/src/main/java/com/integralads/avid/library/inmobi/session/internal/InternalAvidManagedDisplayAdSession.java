package com.integralads.avid.library.inmobi.session.internal;

public class InternalAvidManagedDisplayAdSession extends InternalAvidManagedAdSession {
    public SessionType getSessionType() {
        return SessionType.MANAGED_DISPLAY;
    }

    public MediaType getMediaType() {
        return MediaType.DISPLAY;
    }
}
