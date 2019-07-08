package com.google.ads.interactivemedia.v3.impl.data;

import com.google.ads.interactivemedia.v3.impl.data.TestingConfiguration.Builder;
import java.util.List;
import java.util.Map;

/* compiled from: IMASDK */
final class s implements Builder {
    private Boolean disableExperiments;
    private Boolean disableOnScreenDetection;
    private Boolean enableMonitorAppLifecycle;
    private Map<String, Object> extraParams;
    private List<Long> forceExperimentIds;
    private Boolean forceTvMode;
    private Boolean ignoreStrictModeFalsePositives;
    private Boolean useTestStreamManager;
    private Boolean useVideoElementMock;
    private Float videoElementMockDuration;

    s() {
    }

    public final Builder disableExperiments(boolean z) {
        this.disableExperiments = Boolean.valueOf(z);
        return this;
    }

    public final Builder disableOnScreenDetection(boolean z) {
        this.disableOnScreenDetection = Boolean.valueOf(z);
        return this;
    }

    public final Builder useVideoElementMock(boolean z) {
        this.useVideoElementMock = Boolean.valueOf(z);
        return this;
    }

    public final Builder videoElementMockDuration(float f) {
        this.videoElementMockDuration = Float.valueOf(f);
        return this;
    }

    public final Builder useTestStreamManager(boolean z) {
        this.useTestStreamManager = Boolean.valueOf(z);
        return this;
    }

    public final Builder enableMonitorAppLifecycle(boolean z) {
        this.enableMonitorAppLifecycle = Boolean.valueOf(z);
        return this;
    }

    public final Builder forceExperimentIds(List<Long> list) {
        this.forceExperimentIds = list;
        return this;
    }

    public final Builder forceTvMode(boolean z) {
        this.forceTvMode = Boolean.valueOf(z);
        return this;
    }

    public final Builder ignoreStrictModeFalsePositives(boolean z) {
        this.ignoreStrictModeFalsePositives = Boolean.valueOf(z);
        return this;
    }

    public final Builder extraParams(Map<String, Object> map) {
        this.extraParams = map;
        return this;
    }

    public final TestingConfiguration build() {
        String str = "";
        if (this.disableExperiments == null) {
            str = String.valueOf(str).concat(" disableExperiments");
        }
        if (this.disableOnScreenDetection == null) {
            str = String.valueOf(str).concat(" disableOnScreenDetection");
        }
        if (this.useVideoElementMock == null) {
            str = String.valueOf(str).concat(" useVideoElementMock");
        }
        if (this.videoElementMockDuration == null) {
            str = String.valueOf(str).concat(" videoElementMockDuration");
        }
        if (this.useTestStreamManager == null) {
            str = String.valueOf(str).concat(" useTestStreamManager");
        }
        if (this.enableMonitorAppLifecycle == null) {
            str = String.valueOf(str).concat(" enableMonitorAppLifecycle");
        }
        if (this.forceTvMode == null) {
            str = String.valueOf(str).concat(" forceTvMode");
        }
        if (this.ignoreStrictModeFalsePositives == null) {
            str = String.valueOf(str).concat(" ignoreStrictModeFalsePositives");
        }
        if (!str.isEmpty()) {
            String str2 = "Missing required properties:";
            String valueOf = String.valueOf(str);
            throw new IllegalStateException(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        }
        r rVar = new r(this.disableExperiments.booleanValue(), this.disableOnScreenDetection.booleanValue(), this.useVideoElementMock.booleanValue(), this.videoElementMockDuration.floatValue(), this.useTestStreamManager.booleanValue(), this.enableMonitorAppLifecycle.booleanValue(), this.forceExperimentIds, this.forceTvMode.booleanValue(), this.ignoreStrictModeFalsePositives.booleanValue(), this.extraParams, null);
        return rVar;
    }
}
