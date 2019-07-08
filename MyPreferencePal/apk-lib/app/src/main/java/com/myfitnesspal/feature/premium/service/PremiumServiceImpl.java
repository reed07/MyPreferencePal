package com.myfitnesspal.feature.premium.service;

import lanchon.dexpatcher.annotation.*;

@DexEdit(defaultAction = DexAction.IGNORE, contentOnly = true)
public class PremiumServiceImpl {

    @DexReplace
    public boolean isPremiumAvailable()
    {
        return true;
    }

    @DexReplace
    public boolean isPremiumSubscribed()
    {
        return true;
    }

    @DexReplace
    public boolean isPremiumAvailableGeographically()
    {
        return true;
    }
}
