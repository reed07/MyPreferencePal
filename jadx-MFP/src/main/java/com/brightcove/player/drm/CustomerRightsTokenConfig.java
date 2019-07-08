package com.brightcove.player.drm;

import android.support.annotation.Nullable;

public final class CustomerRightsTokenConfig {
    private final OutputProtection outputProtection;

    public static class Builder {
        private OutputProtection outputProtection = null;

        public Builder setOutputProtection(OutputProtection outputProtection2) {
            this.outputProtection = outputProtection2;
            return this;
        }

        public CustomerRightsTokenConfig build() {
            return new CustomerRightsTokenConfig(this.outputProtection);
        }
    }

    private CustomerRightsTokenConfig(OutputProtection outputProtection2) {
        this.outputProtection = outputProtection2;
    }

    @Nullable
    public OutputProtection getOutputProtection() {
        return this.outputProtection;
    }
}
