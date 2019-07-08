package com.myfitnesspal.feature.appgallery.event;

import com.myfitnesspal.shared.model.v2.MfpPlatformApp;

public class NavigateToPartnerAppDetailsEvent {
    private MfpPlatformApp app;

    public NavigateToPartnerAppDetailsEvent(MfpPlatformApp mfpPlatformApp) {
        this.app = mfpPlatformApp;
    }

    public MfpPlatformApp getApp() {
        return this.app;
    }
}
