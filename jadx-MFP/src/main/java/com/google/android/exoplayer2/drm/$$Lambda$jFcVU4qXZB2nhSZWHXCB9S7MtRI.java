package com.google.android.exoplayer2.drm;

import com.google.android.exoplayer2.util.EventDispatcher.Event;

/* renamed from: com.google.android.exoplayer2.drm.-$$Lambda$jFcVU4qXZB2nhSZWHXCB9S7MtRI reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$jFcVU4qXZB2nhSZWHXCB9S7MtRI implements Event {
    public static final /* synthetic */ $$Lambda$jFcVU4qXZB2nhSZWHXCB9S7MtRI INSTANCE = new $$Lambda$jFcVU4qXZB2nhSZWHXCB9S7MtRI();

    private /* synthetic */ $$Lambda$jFcVU4qXZB2nhSZWHXCB9S7MtRI() {
    }

    public final void sendTo(Object obj) {
        ((DefaultDrmSessionEventListener) obj).onDrmSessionAcquired();
    }
}
