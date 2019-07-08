package com.google.ads.interactivemedia.v3.impl.data;

import com.google.ads.interactivemedia.v3.internal.afs;
import java.util.List;
import java.util.Map;

@afs(a = r.class, b = {"extraParams", "isTv", "ignoreStrictModeFalsePositives"})
/* compiled from: IMASDK */
public abstract class TestingConfiguration {
    public static final String PARAMETER_KEY = "tcnfp";

    /* compiled from: IMASDK */
    public interface Builder {
        TestingConfiguration build();

        Builder disableExperiments(boolean z);

        Builder disableOnScreenDetection(boolean z);

        Builder enableMonitorAppLifecycle(boolean z);

        Builder extraParams(Map<String, Object> map);

        Builder forceExperimentIds(List<Long> list);

        Builder forceTvMode(boolean z);

        Builder ignoreStrictModeFalsePositives(boolean z);

        Builder useTestStreamManager(boolean z);

        Builder useVideoElementMock(boolean z);

        Builder videoElementMockDuration(float f);
    }

    public static Builder builder() {
        return new s().disableExperiments(false).disableOnScreenDetection(false).useVideoElementMock(false).videoElementMockDuration(30.0f).useTestStreamManager(false).ignoreStrictModeFalsePositives(false).forceTvMode(false).enableMonitorAppLifecycle(true);
    }

    public abstract boolean disableExperiments();

    public abstract boolean disableOnScreenDetection();

    public abstract boolean enableMonitorAppLifecycle();

    public abstract Map<String, Object> extraParams();

    public abstract List<Long> forceExperimentIds();

    public abstract boolean forceTvMode();

    public abstract boolean ignoreStrictModeFalsePositives();

    public abstract boolean useTestStreamManager();

    public abstract boolean useVideoElementMock();

    public abstract float videoElementMockDuration();

    TestingConfiguration() {
    }

    public Builder copy() {
        return new s().disableExperiments(disableExperiments()).disableOnScreenDetection(disableOnScreenDetection()).useVideoElementMock(useVideoElementMock()).videoElementMockDuration(videoElementMockDuration()).useTestStreamManager(useTestStreamManager()).enableMonitorAppLifecycle(enableMonitorAppLifecycle()).forceTvMode(forceTvMode()).ignoreStrictModeFalsePositives(ignoreStrictModeFalsePositives()).extraParams(extraParams());
    }
}
