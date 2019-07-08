package com.myfitnesspal.shared.util;

import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class ImageCropHelper_Factory implements Factory<ImageCropHelper> {
    private final Provider<NavigationHelper> navigationHelperProvider;

    public ImageCropHelper_Factory(Provider<NavigationHelper> provider) {
        this.navigationHelperProvider = provider;
    }

    public ImageCropHelper get() {
        return provideInstance(this.navigationHelperProvider);
    }

    public static ImageCropHelper provideInstance(Provider<NavigationHelper> provider) {
        return new ImageCropHelper((NavigationHelper) provider.get());
    }

    public static ImageCropHelper_Factory create(Provider<NavigationHelper> provider) {
        return new ImageCropHelper_Factory(provider);
    }

    public static ImageCropHelper newImageCropHelper(NavigationHelper navigationHelper) {
        return new ImageCropHelper(navigationHelper);
    }
}
