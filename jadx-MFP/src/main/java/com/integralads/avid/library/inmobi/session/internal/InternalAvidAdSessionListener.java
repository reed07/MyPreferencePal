package com.integralads.avid.library.inmobi.session.internal;

public interface InternalAvidAdSessionListener {
    void sessionDidEnd(InternalAvidAdSession internalAvidAdSession);

    void sessionHasBecomeActive(InternalAvidAdSession internalAvidAdSession);

    void sessionHasResignedActive(InternalAvidAdSession internalAvidAdSession);
}
