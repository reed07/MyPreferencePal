package com.myfitnesspal.shared.uacf;

import com.myfitnesspal.shared.constants.Constants.Rollouts;
import dagger.Lazy;
import io.uacf.rollouts.sdk.UacfVariantSdk;
import io.uacf.rollouts.sdk.model.UacfVariant;

public class UacfRolloutUtil {
    private Lazy<UacfVariantSdk> variantSdk;

    public UacfRolloutUtil(Lazy<UacfVariantSdk> lazy) {
        this.variantSdk = lazy;
    }

    public boolean shouldConfigureNiSdkWithConfigurationValues() {
        UacfVariant variant = ((UacfVariantSdk) this.variantSdk.get()).getVariant(Rollouts.NI_SDK_USE_CONFIG);
        return (variant == null || variant.getVariantName() == null || !variant.getVariantName().equals("enabled")) ? false : true;
    }

    public boolean shouldShowUacfEmailVerificationScreen() {
        UacfVariant variant = ((UacfVariantSdk) this.variantSdk.get()).getVariant(Rollouts.EMAIL_VERIFICATION);
        return (variant == null || variant.getVariantName() == null || !variant.getVariantName().equals("enabled")) ? false : true;
    }

    public boolean shouldShowUacfEmailVerificationScreenWorldWide() {
        UacfVariant variant = ((UacfVariantSdk) this.variantSdk.get()).getVariant(Rollouts.EMAIL_VERIFICATION_WORLD_WIDE);
        return (variant == null || variant.getVariantName() == null || !variant.getVariantName().equals("enabled")) ? false : true;
    }
}
