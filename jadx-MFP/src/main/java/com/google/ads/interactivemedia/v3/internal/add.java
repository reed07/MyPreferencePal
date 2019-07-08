package com.google.ads.interactivemedia.v3.internal;

import com.google.ads.interactivemedia.v3.api.AdErrorEvent;
import com.google.ads.interactivemedia.v3.api.AdErrorEvent.AdErrorListener;
import java.util.ArrayList;
import java.util.List;

/* compiled from: IMASDK */
public final class add {
    private final List<AdErrorListener> a = new ArrayList(1);

    public final void a(AdErrorListener adErrorListener) {
        this.a.add(adErrorListener);
    }

    public final void b(AdErrorListener adErrorListener) {
        this.a.remove(adErrorListener);
    }

    public final void a(AdErrorEvent adErrorEvent) {
        for (AdErrorListener onAdError : this.a) {
            onAdError.onAdError(adErrorEvent);
        }
    }

    public final String toString() {
        String valueOf = String.valueOf(this.a);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 38);
        sb.append("ErrorListenerSupport [errorListeners=");
        sb.append(valueOf);
        sb.append("]");
        return sb.toString();
    }
}
