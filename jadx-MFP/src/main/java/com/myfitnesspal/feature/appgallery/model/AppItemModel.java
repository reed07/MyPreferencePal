package com.myfitnesspal.feature.appgallery.model;

import com.myfitnesspal.framework.mvvm.BaseViewModel;
import com.myfitnesspal.shared.model.v2.MfpAppImage;
import com.myfitnesspal.shared.model.v2.MfpPlatformApp;

public class AppItemModel extends BaseViewModel {
    private MfpPlatformApp model;

    public AppItemModel(MfpPlatformApp mfpPlatformApp) {
        this.model = mfpPlatformApp;
    }

    public final String getImageUrl() {
        MfpAppImage iconImage = this.model.getIconImage();
        if (iconImage != null) {
            return iconImage.getFilename();
        }
        return null;
    }

    public final String getName() {
        return this.model.getName();
    }

    public String getDescription() {
        return this.model.getAppShortDescription();
    }

    public final MfpPlatformApp getModel() {
        return this.model;
    }
}
