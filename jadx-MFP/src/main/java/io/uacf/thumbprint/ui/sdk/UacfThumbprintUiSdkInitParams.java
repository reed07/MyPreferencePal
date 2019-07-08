package io.uacf.thumbprint.ui.sdk;

import android.support.annotation.NonNull;
import com.uacf.identity.sdk.UacfIdentitySdk;
import io.uacf.core.interfaces.UacfClientEventsCallback;
import io.uacf.thumbprint.ui.sdk.uiconfig.UacfThumbprintUiConfig;

public final class UacfThumbprintUiSdkInitParams {
    /* access modifiers changed from: private */
    public UacfClientEventsCallback clientEventsCallback;
    /* access modifiers changed from: private */
    public UacfIdentitySdk identitySdk;
    /* access modifiers changed from: private */
    public UacfThumbprintUiConfig uiConfig;

    public static class Builder {
        private UacfClientEventsCallback clientEventsCallback;
        private UacfIdentitySdk identitySdk;
        private UacfThumbprintUiConfig uiConfig;

        public Builder setIdentitySdk(@NonNull UacfIdentitySdk uacfIdentitySdk) {
            this.identitySdk = uacfIdentitySdk;
            return this;
        }

        public Builder setClientEventsCallback(@NonNull UacfClientEventsCallback uacfClientEventsCallback) {
            this.clientEventsCallback = uacfClientEventsCallback;
            return this;
        }

        public UacfThumbprintUiSdkInitParams build() {
            if (this.identitySdk != null) {
                UacfThumbprintUiSdkInitParams uacfThumbprintUiSdkInitParams = new UacfThumbprintUiSdkInitParams();
                uacfThumbprintUiSdkInitParams.identitySdk = this.identitySdk;
                uacfThumbprintUiSdkInitParams.clientEventsCallback = this.clientEventsCallback;
                uacfThumbprintUiSdkInitParams.uiConfig = this.uiConfig;
                return uacfThumbprintUiSdkInitParams;
            }
            throw new IllegalStateException("UacfIdentitySdk cannot be null");
        }
    }

    public UacfIdentitySdk getIdentitySdk() {
        return this.identitySdk;
    }

    public UacfClientEventsCallback getUacfClientEventsCallback() {
        return this.clientEventsCallback;
    }

    public UacfThumbprintUiConfig getThumbprintUiConfig() {
        return this.uiConfig;
    }
}
