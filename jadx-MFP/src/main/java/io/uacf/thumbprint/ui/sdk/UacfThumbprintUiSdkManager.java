package io.uacf.thumbprint.ui.sdk;

public final class UacfThumbprintUiSdkManager {
    private static UacfThumbprintUiSdk thumbprintUiSdk;

    public static UacfThumbprintUiSdk getInstance() {
        UacfThumbprintUiSdk uacfThumbprintUiSdk = thumbprintUiSdk;
        if (uacfThumbprintUiSdk != null) {
            return uacfThumbprintUiSdk;
        }
        throw new IllegalStateException("UacfThumbprintUiSdkManager.initializeSdk() must be called before calling any methods");
    }

    public static void initializeSdk(UacfThumbprintUiSdkInitParams uacfThumbprintUiSdkInitParams) {
        if (thumbprintUiSdk == null) {
            thumbprintUiSdk = new UacfThumbprintUiSdkImpl(uacfThumbprintUiSdkInitParams.getIdentitySdk(), uacfThumbprintUiSdkInitParams.getUacfClientEventsCallback(), uacfThumbprintUiSdkInitParams.getThumbprintUiConfig());
            return;
        }
        throw new IllegalStateException("UacfThumbprintUiSdk has already been setup.");
    }
}
