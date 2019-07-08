package com.myfitnesspal.feature.premium.service;

public interface PremiumService {

    public enum Availability {
        Hidden,
        Locked,
        Revoked,
        Available
    }

    Availability getFeatureAvailability(PremiumFeature premiumFeature);

    void invalidateAvailability();

    boolean isFeatureAvailable(PremiumFeature premiumFeature);

    boolean isFeatureSubscribed(PremiumFeature premiumFeature);

    boolean isPremiumAvailable();

    boolean isPremiumAvailableGeographically();

    boolean isPremiumSubscribed();
}
