package com.myfitnesspal.shared.service.install;

import android.content.Context;
import com.myfitnesspal.feature.registration.service.InstallManager;
import com.myfitnesspal.shared.deeplink.DeepLinkManager;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.inject.Inject;

public class UtmInstallReceiver extends AbstractInstallReferrerReceiver {
    @Inject
    DeepLinkManager deepLinkManager;
    @Inject
    InstallManager installManager;

    /* access modifiers changed from: protected */
    public void onReceivedReferral(Context context, String str, String str2, String str3, String str4, String str5) {
        try {
            if (Strings.notEmpty(str4) && str4.startsWith("mfp://")) {
                this.deepLinkManager.setDestinationDeepLink(URLDecoder.decode(str4, "UTF-8"));
            }
            this.installManager.trackInstallOrUpdate(str, str2, str3, str4, str5);
        } catch (UnsupportedEncodingException e) {
            Ln.w(e, "Could not decode URL", new Object[0]);
        }
    }
}
