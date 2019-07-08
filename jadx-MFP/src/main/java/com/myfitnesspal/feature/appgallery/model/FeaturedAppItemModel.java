package com.myfitnesspal.feature.appgallery.model;

import com.myfitnesspal.shared.model.v2.MfpPlatformApp;
import com.uacf.core.util.CollectionUtils;
import java.util.ArrayList;
import java.util.Collection;

public class FeaturedAppItemModel extends AppItemModel {
    public FeaturedAppItemModel(MfpPlatformApp mfpPlatformApp) {
        super(mfpPlatformApp);
    }

    public String getDescription() {
        ArrayList appCategories = getModel().getAppCategories();
        return CollectionUtils.notEmpty((Collection<?>) appCategories) ? (String) appCategories.get(0) : super.getDescription();
    }
}
