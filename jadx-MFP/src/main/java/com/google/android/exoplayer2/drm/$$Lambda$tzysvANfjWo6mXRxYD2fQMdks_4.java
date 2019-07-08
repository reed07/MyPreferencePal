package com.google.android.exoplayer2.drm;

import com.google.android.exoplayer2.util.EventDispatcher.Event;

/* renamed from: com.google.android.exoplayer2.drm.-$$Lambda$tzysvANfjWo6mXRxYD2fQMdks_4 reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$tzysvANfjWo6mXRxYD2fQMdks_4 implements Event {
    public static final /* synthetic */ $$Lambda$tzysvANfjWo6mXRxYD2fQMdks_4 INSTANCE = new $$Lambda$tzysvANfjWo6mXRxYD2fQMdks_4();

    private /* synthetic */ $$Lambda$tzysvANfjWo6mXRxYD2fQMdks_4() {
    }

    public final void sendTo(Object obj) {
        ((DefaultDrmSessionEventListener) obj).onDrmKeysRestored();
    }
}
