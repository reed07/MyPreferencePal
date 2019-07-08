package com.google.ads.interactivemedia.v3.impl.data;

/* compiled from: IMASDK */
final class g implements b {
    private String appState;
    private String eventId;
    private Long nativeTime;
    private Boolean nativeViewAttached;
    private v nativeViewBounds;
    private Boolean nativeViewHidden;
    private v nativeViewVisibleBounds;
    private Double nativeVolume;
    private String queryId;
    private String vastEvent;

    g() {
    }

    public final b queryId(String str) {
        if (str != null) {
            this.queryId = str;
            return this;
        }
        throw new NullPointerException("Null queryId");
    }

    public final b eventId(String str) {
        if (str != null) {
            this.eventId = str;
            return this;
        }
        throw new NullPointerException("Null eventId");
    }

    public final b vastEvent(String str) {
        if (str != null) {
            this.vastEvent = str;
            return this;
        }
        throw new NullPointerException("Null vastEvent");
    }

    public final b appState(String str) {
        if (str != null) {
            this.appState = str;
            return this;
        }
        throw new NullPointerException("Null appState");
    }

    public final b nativeTime(long j) {
        this.nativeTime = Long.valueOf(j);
        return this;
    }

    public final b nativeVolume(double d) {
        this.nativeVolume = Double.valueOf(d);
        return this;
    }

    public final b nativeViewAttached(boolean z) {
        this.nativeViewAttached = Boolean.valueOf(z);
        return this;
    }

    public final b nativeViewHidden(boolean z) {
        this.nativeViewHidden = Boolean.valueOf(z);
        return this;
    }

    public final b nativeViewBounds(v vVar) {
        if (vVar != null) {
            this.nativeViewBounds = vVar;
            return this;
        }
        throw new NullPointerException("Null nativeViewBounds");
    }

    public final b nativeViewVisibleBounds(v vVar) {
        if (vVar != null) {
            this.nativeViewVisibleBounds = vVar;
            return this;
        }
        throw new NullPointerException("Null nativeViewVisibleBounds");
    }

    public final a build() {
        String str = "";
        if (this.queryId == null) {
            str = String.valueOf(str).concat(" queryId");
        }
        if (this.eventId == null) {
            str = String.valueOf(str).concat(" eventId");
        }
        if (this.vastEvent == null) {
            str = String.valueOf(str).concat(" vastEvent");
        }
        if (this.appState == null) {
            str = String.valueOf(str).concat(" appState");
        }
        if (this.nativeTime == null) {
            str = String.valueOf(str).concat(" nativeTime");
        }
        if (this.nativeVolume == null) {
            str = String.valueOf(str).concat(" nativeVolume");
        }
        if (this.nativeViewAttached == null) {
            str = String.valueOf(str).concat(" nativeViewAttached");
        }
        if (this.nativeViewHidden == null) {
            str = String.valueOf(str).concat(" nativeViewHidden");
        }
        if (this.nativeViewBounds == null) {
            str = String.valueOf(str).concat(" nativeViewBounds");
        }
        if (this.nativeViewVisibleBounds == null) {
            str = String.valueOf(str).concat(" nativeViewVisibleBounds");
        }
        if (!str.isEmpty()) {
            String str2 = "Missing required properties:";
            String valueOf = String.valueOf(str);
            throw new IllegalStateException(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        }
        e eVar = new e(this.queryId, this.eventId, this.vastEvent, this.appState, this.nativeTime.longValue(), this.nativeVolume.doubleValue(), this.nativeViewAttached.booleanValue(), this.nativeViewHidden.booleanValue(), this.nativeViewBounds, this.nativeViewVisibleBounds, null);
        return eVar;
    }
}
