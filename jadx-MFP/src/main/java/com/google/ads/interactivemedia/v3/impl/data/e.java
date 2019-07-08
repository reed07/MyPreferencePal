package com.google.ads.interactivemedia.v3.impl.data;

/* compiled from: IMASDK */
final class e extends a {
    private final String appState;
    private final String eventId;
    private final long nativeTime;
    private final boolean nativeViewAttached;
    private final v nativeViewBounds;
    private final boolean nativeViewHidden;
    private final v nativeViewVisibleBounds;
    private final double nativeVolume;
    private final String queryId;
    private final String vastEvent;

    private e(String str, String str2, String str3, String str4, long j, double d, boolean z, boolean z2, v vVar, v vVar2) {
        this.queryId = str;
        this.eventId = str2;
        this.vastEvent = str3;
        this.appState = str4;
        this.nativeTime = j;
        this.nativeVolume = d;
        this.nativeViewAttached = z;
        this.nativeViewHidden = z2;
        this.nativeViewBounds = vVar;
        this.nativeViewVisibleBounds = vVar2;
    }

    public final String queryId() {
        return this.queryId;
    }

    public final String eventId() {
        return this.eventId;
    }

    public final String vastEvent() {
        return this.vastEvent;
    }

    public final String appState() {
        return this.appState;
    }

    public final long nativeTime() {
        return this.nativeTime;
    }

    public final double nativeVolume() {
        return this.nativeVolume;
    }

    public final boolean nativeViewAttached() {
        return this.nativeViewAttached;
    }

    public final boolean nativeViewHidden() {
        return this.nativeViewHidden;
    }

    public final v nativeViewBounds() {
        return this.nativeViewBounds;
    }

    public final v nativeViewVisibleBounds() {
        return this.nativeViewVisibleBounds;
    }

    public final String toString() {
        String str = this.queryId;
        String str2 = this.eventId;
        String str3 = this.vastEvent;
        String str4 = this.appState;
        long j = this.nativeTime;
        double d = this.nativeVolume;
        boolean z = this.nativeViewAttached;
        boolean z2 = this.nativeViewHidden;
        String valueOf = String.valueOf(this.nativeViewBounds);
        String valueOf2 = String.valueOf(this.nativeViewVisibleBounds);
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 229 + String.valueOf(str2).length() + String.valueOf(str3).length() + String.valueOf(str4).length() + String.valueOf(valueOf).length() + String.valueOf(valueOf2).length());
        sb.append("ActivityMonitorData{queryId=");
        sb.append(str);
        sb.append(", eventId=");
        sb.append(str2);
        sb.append(", vastEvent=");
        sb.append(str3);
        sb.append(", appState=");
        sb.append(str4);
        sb.append(", nativeTime=");
        sb.append(j);
        sb.append(", nativeVolume=");
        sb.append(d);
        sb.append(", nativeViewAttached=");
        sb.append(z);
        sb.append(", nativeViewHidden=");
        sb.append(z2);
        sb.append(", nativeViewBounds=");
        sb.append(valueOf);
        sb.append(", nativeViewVisibleBounds=");
        sb.append(valueOf2);
        sb.append("}");
        return sb.toString();
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return this.queryId.equals(aVar.queryId()) && this.eventId.equals(aVar.eventId()) && this.vastEvent.equals(aVar.vastEvent()) && this.appState.equals(aVar.appState()) && this.nativeTime == aVar.nativeTime() && Double.doubleToLongBits(this.nativeVolume) == Double.doubleToLongBits(aVar.nativeVolume()) && this.nativeViewAttached == aVar.nativeViewAttached() && this.nativeViewHidden == aVar.nativeViewHidden() && this.nativeViewBounds.equals(aVar.nativeViewBounds()) && this.nativeViewVisibleBounds.equals(aVar.nativeViewVisibleBounds());
    }

    public final int hashCode() {
        int hashCode = (((((((this.queryId.hashCode() ^ 1000003) * 1000003) ^ this.eventId.hashCode()) * 1000003) ^ this.vastEvent.hashCode()) * 1000003) ^ this.appState.hashCode()) * 1000003;
        long j = this.nativeTime;
        int i = 1231;
        int doubleToLongBits = (((((hashCode ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ ((int) ((Double.doubleToLongBits(this.nativeVolume) >>> 32) ^ Double.doubleToLongBits(this.nativeVolume)))) * 1000003) ^ (this.nativeViewAttached ? 1231 : 1237)) * 1000003;
        if (!this.nativeViewHidden) {
            i = 1237;
        }
        return ((((doubleToLongBits ^ i) * 1000003) ^ this.nativeViewBounds.hashCode()) * 1000003) ^ this.nativeViewVisibleBounds.hashCode();
    }

    /* synthetic */ e(String str, String str2, String str3, String str4, long j, double d, boolean z, boolean z2, v vVar, v vVar2, f fVar) {
        this(str, str2, str3, str4, j, d, z, z2, vVar, vVar2);
    }
}
