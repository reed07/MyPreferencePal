package com.google.android.exoplayer2.drm;

import com.google.android.exoplayer2.util.EventDispatcher.Event;

/* renamed from: com.google.android.exoplayer2.drm.-$$Lambda$wyKVEWJALn1OyjwryLo2GUxlQ2M reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$wyKVEWJALn1OyjwryLo2GUxlQ2M implements Event {
    public static final /* synthetic */ $$Lambda$wyKVEWJALn1OyjwryLo2GUxlQ2M INSTANCE = new $$Lambda$wyKVEWJALn1OyjwryLo2GUxlQ2M();

    private /* synthetic */ $$Lambda$wyKVEWJALn1OyjwryLo2GUxlQ2M() {
    }

    public final void sendTo(Object obj) {
        ((DefaultDrmSessionEventListener) obj).onDrmKeysLoaded();
    }
}
