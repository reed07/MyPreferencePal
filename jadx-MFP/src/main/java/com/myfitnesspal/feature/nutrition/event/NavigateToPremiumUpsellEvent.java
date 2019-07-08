package com.myfitnesspal.feature.nutrition.event;

import com.myfitnesspal.feature.premium.service.PremiumFeature;
import com.myfitnesspal.shared.event.MfpEventBase;

public class NavigateToPremiumUpsellEvent extends MfpEventBase {
    PremiumFeature premiumFeature;

    public NavigateToPremiumUpsellEvent(PremiumFeature premiumFeature2) {
        this.premiumFeature = premiumFeature2;
    }

    public PremiumFeature getPremiumFeature() {
        return this.premiumFeature;
    }
}
