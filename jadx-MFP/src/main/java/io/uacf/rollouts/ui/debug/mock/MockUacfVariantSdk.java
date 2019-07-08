package io.uacf.rollouts.ui.debug.mock;

import io.uacf.core.api.UacfApiException;
import io.uacf.rollouts.sdk.UacfVariantSdk;
import io.uacf.rollouts.sdk.model.UacfVariant;
import io.uacf.rollouts.sdk.model.UacfVariant.Builder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockUacfVariantSdk implements UacfVariantSdk {
    private Map<String, UacfVariant> mockVariants = new HashMap();

    public MockUacfVariantSdk() {
        loadMockVariants();
    }

    private void loadMockVariants() {
        this.mockVariants.clear();
        this.mockVariants.put("rollout1", new Builder().withRolloutName("rollout1").withTrackEvent(true).withVariant("variant2").withVariantIndex(1).withVersion(3).build());
        this.mockVariants.put("rollout2", new Builder().withRolloutName("rollout2").withTrackEvent(true).withVersion(1).build());
        this.mockVariants.put("rollout3", new Builder().withRolloutName("rollout3").withTrackEvent(false).withVersion(2).build());
        this.mockVariants.put("rollout4", new Builder().withRolloutName("rollout4").withTrackEvent(false).withVariant("variant2").withVariantIndex(0).withVersion(1).build());
        this.mockVariants.put("rollout5", new Builder().withRolloutName("rollout5").withTrackEvent(true).withVariant("variant4").withVariantIndex(3).withVersion(5).build());
    }

    public List<UacfVariant> getVariants() {
        return new ArrayList(this.mockVariants.values());
    }

    public UacfVariant getVariant(String str) {
        return (UacfVariant) this.mockVariants.get(str);
    }

    public void fetchVariants() throws UacfApiException {
        loadMockVariants();
    }

    public void forceFetchVariants() throws UacfApiException {
        loadMockVariants();
    }
}
