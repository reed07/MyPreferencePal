package com.google.ads.interactivemedia.v3.impl.data;

import com.google.logging.type.LogSeverity;
import java.util.List;
import java.util.Map;

/* compiled from: IMASDK */
final class r extends TestingConfiguration {
    private final boolean disableExperiments;
    private final boolean disableOnScreenDetection;
    private final boolean enableMonitorAppLifecycle;
    private final Map<String, Object> extraParams;
    private final List<Long> forceExperimentIds;
    private final boolean forceTvMode;
    private final boolean ignoreStrictModeFalsePositives;
    private final boolean useTestStreamManager;
    private final boolean useVideoElementMock;
    private final float videoElementMockDuration;

    private r(boolean z, boolean z2, boolean z3, float f, boolean z4, boolean z5, List<Long> list, boolean z6, boolean z7, Map<String, Object> map) {
        this.disableExperiments = z;
        this.disableOnScreenDetection = z2;
        this.useVideoElementMock = z3;
        this.videoElementMockDuration = f;
        this.useTestStreamManager = z4;
        this.enableMonitorAppLifecycle = z5;
        this.forceExperimentIds = list;
        this.forceTvMode = z6;
        this.ignoreStrictModeFalsePositives = z7;
        this.extraParams = map;
    }

    public final boolean disableExperiments() {
        return this.disableExperiments;
    }

    public final boolean disableOnScreenDetection() {
        return this.disableOnScreenDetection;
    }

    public final boolean useVideoElementMock() {
        return this.useVideoElementMock;
    }

    public final float videoElementMockDuration() {
        return this.videoElementMockDuration;
    }

    public final boolean useTestStreamManager() {
        return this.useTestStreamManager;
    }

    public final boolean enableMonitorAppLifecycle() {
        return this.enableMonitorAppLifecycle;
    }

    public final List<Long> forceExperimentIds() {
        return this.forceExperimentIds;
    }

    public final boolean forceTvMode() {
        return this.forceTvMode;
    }

    public final boolean ignoreStrictModeFalsePositives() {
        return this.ignoreStrictModeFalsePositives;
    }

    public final Map<String, Object> extraParams() {
        return this.extraParams;
    }

    public final String toString() {
        boolean z = this.disableExperiments;
        boolean z2 = this.disableOnScreenDetection;
        boolean z3 = this.useVideoElementMock;
        float f = this.videoElementMockDuration;
        boolean z4 = this.useTestStreamManager;
        boolean z5 = this.enableMonitorAppLifecycle;
        String valueOf = String.valueOf(this.forceExperimentIds);
        boolean z6 = this.forceTvMode;
        boolean z7 = this.ignoreStrictModeFalsePositives;
        String valueOf2 = String.valueOf(this.extraParams);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + LogSeverity.NOTICE_VALUE + String.valueOf(valueOf2).length());
        sb.append("TestingConfiguration{disableExperiments=");
        sb.append(z);
        sb.append(", disableOnScreenDetection=");
        sb.append(z2);
        sb.append(", useVideoElementMock=");
        sb.append(z3);
        sb.append(", videoElementMockDuration=");
        sb.append(f);
        sb.append(", useTestStreamManager=");
        sb.append(z4);
        sb.append(", enableMonitorAppLifecycle=");
        sb.append(z5);
        sb.append(", forceExperimentIds=");
        sb.append(valueOf);
        sb.append(", forceTvMode=");
        sb.append(z6);
        sb.append(", ignoreStrictModeFalsePositives=");
        sb.append(z7);
        sb.append(", extraParams=");
        sb.append(valueOf2);
        sb.append("}");
        return sb.toString();
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TestingConfiguration)) {
            return false;
        }
        TestingConfiguration testingConfiguration = (TestingConfiguration) obj;
        if (this.disableExperiments == testingConfiguration.disableExperiments() && this.disableOnScreenDetection == testingConfiguration.disableOnScreenDetection() && this.useVideoElementMock == testingConfiguration.useVideoElementMock() && Float.floatToIntBits(this.videoElementMockDuration) == Float.floatToIntBits(testingConfiguration.videoElementMockDuration()) && this.useTestStreamManager == testingConfiguration.useTestStreamManager() && this.enableMonitorAppLifecycle == testingConfiguration.enableMonitorAppLifecycle()) {
            List<Long> list = this.forceExperimentIds;
            if (list != null ? list.equals(testingConfiguration.forceExperimentIds()) : testingConfiguration.forceExperimentIds() == null) {
                if (this.forceTvMode == testingConfiguration.forceTvMode() && this.ignoreStrictModeFalsePositives == testingConfiguration.ignoreStrictModeFalsePositives()) {
                    Map<String, Object> map = this.extraParams;
                    return map != null ? map.equals(testingConfiguration.extraParams()) : testingConfiguration.extraParams() == null;
                }
            }
        }
    }

    public final int hashCode() {
        int i = 1231;
        int floatToIntBits = ((((((((((((this.disableExperiments ? 1231 : 1237) ^ 1000003) * 1000003) ^ (this.disableOnScreenDetection ? 1231 : 1237)) * 1000003) ^ (this.useVideoElementMock ? 1231 : 1237)) * 1000003) ^ Float.floatToIntBits(this.videoElementMockDuration)) * 1000003) ^ (this.useTestStreamManager ? 1231 : 1237)) * 1000003) ^ (this.enableMonitorAppLifecycle ? 1231 : 1237)) * 1000003;
        List<Long> list = this.forceExperimentIds;
        int i2 = 0;
        int hashCode = (((floatToIntBits ^ (list == null ? 0 : list.hashCode())) * 1000003) ^ (this.forceTvMode ? 1231 : 1237)) * 1000003;
        if (!this.ignoreStrictModeFalsePositives) {
            i = 1237;
        }
        int i3 = (hashCode ^ i) * 1000003;
        Map<String, Object> map = this.extraParams;
        if (map != null) {
            i2 = map.hashCode();
        }
        return i3 ^ i2;
    }

    /* synthetic */ r(boolean z, boolean z2, boolean z3, float f, boolean z4, boolean z5, List list, boolean z6, boolean z7, Map map, f fVar) {
        this(z, z2, z3, f, z4, z5, list, z6, z7, map);
    }
}
