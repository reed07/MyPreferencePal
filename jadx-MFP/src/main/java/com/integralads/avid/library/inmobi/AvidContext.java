package com.integralads.avid.library.inmobi;

import android.content.Context;
import com.integralads.avid.library.mopub.BuildConfig;

public class AvidContext {
    private static final AvidContext instance = new AvidContext();
    private String bundleId;

    public String getAvidVersion() {
        return BuildConfig.VERSION_NAME;
    }

    public String getPartnerName() {
        return "inmobi";
    }

    public static AvidContext getInstance() {
        return instance;
    }

    public void init(Context context) {
        if (this.bundleId == null) {
            this.bundleId = context.getApplicationContext().getPackageName();
        }
    }

    public String getBundleId() {
        return this.bundleId;
    }
}
