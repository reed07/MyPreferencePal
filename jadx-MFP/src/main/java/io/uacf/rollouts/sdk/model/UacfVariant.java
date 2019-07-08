package io.uacf.rollouts.sdk.model;

import android.text.TextUtils;

public class UacfVariant {
    /* access modifiers changed from: private */
    public String rolloutName;
    /* access modifiers changed from: private */
    public int rolloutVersion;
    /* access modifiers changed from: private */
    public boolean trackEvent;
    /* access modifiers changed from: private */
    public int variantIndex;
    /* access modifiers changed from: private */
    public String variantName;

    public static final class Builder {
        private String rolloutName;
        private int rolloutVersion;
        private boolean trackEvent;
        private int variantIndex;
        private String variantName;

        public Builder withRolloutName(String str) {
            this.rolloutName = str;
            return this;
        }

        public Builder withTrackEvent(boolean z) {
            this.trackEvent = z;
            return this;
        }

        public Builder withVersion(int i) {
            this.rolloutVersion = i;
            return this;
        }

        public Builder withVariant(String str) {
            this.variantName = str;
            return this;
        }

        public Builder withVariantIndex(int i) {
            this.variantIndex = i;
            return this;
        }

        public UacfVariant build() {
            UacfVariant uacfVariant = new UacfVariant();
            uacfVariant.rolloutName = this.rolloutName;
            uacfVariant.rolloutVersion = this.rolloutVersion;
            uacfVariant.trackEvent = this.trackEvent;
            uacfVariant.variantIndex = this.variantIndex;
            uacfVariant.variantName = this.variantName;
            return uacfVariant;
        }
    }

    public String getRolloutName() {
        return this.rolloutName;
    }

    public Integer getRolloutVersion() {
        return Integer.valueOf(this.rolloutVersion);
    }

    public String getVariantName() {
        if (TextUtils.isEmpty(this.variantName)) {
            return null;
        }
        return this.variantName;
    }

    public int getVariantIndex() {
        return this.variantIndex;
    }

    public boolean getTrackEvent() {
        return this.trackEvent;
    }
}
