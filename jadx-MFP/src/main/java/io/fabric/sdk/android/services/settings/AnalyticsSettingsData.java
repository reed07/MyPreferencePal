package io.fabric.sdk.android.services.settings;

public class AnalyticsSettingsData {
    public final String analyticsURL;
    public final int flushIntervalSeconds;
    public final boolean flushOnBackground;
    public final boolean forwardToFirebaseAnalytics;
    public final boolean includePurchaseEventsInForwardedEvents;
    public final int maxByteSizePerFile;
    public final int maxFileCountPerSend;
    public final int maxPendingSendFileCount;
    public final int samplingRate;
    public final boolean trackCustomEvents;
    public final boolean trackPredefinedEvents;

    public AnalyticsSettingsData(String str, int i, int i2, int i3, int i4, boolean z, boolean z2, boolean z3, boolean z4, int i5, boolean z5) {
        this.analyticsURL = str;
        this.flushIntervalSeconds = i;
        this.maxByteSizePerFile = i2;
        this.maxFileCountPerSend = i3;
        this.maxPendingSendFileCount = i4;
        this.forwardToFirebaseAnalytics = z;
        this.includePurchaseEventsInForwardedEvents = z2;
        this.trackCustomEvents = z3;
        this.trackPredefinedEvents = z4;
        this.samplingRate = i5;
        this.flushOnBackground = z5;
    }
}
