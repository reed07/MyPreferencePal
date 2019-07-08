package com.myfitnesspal.shared.service.install;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.myfitnesspal.shared.constants.UtmParams;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import com.uacf.core.util.UriUtils;

public abstract class AbstractInstallReferrerReceiver extends BroadcastReceiver {
    /* access modifiers changed from: protected */
    public abstract void onReceivedReferral(Context context, String str, String str2, String str3, String str4, String str5);

    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras == null) {
            Ln.d("No extras, bail", new Object[0]);
            return;
        }
        String string = extras.getString("referrer");
        Ln.d("Referrer is %s ", string);
        if (Strings.isEmpty(string)) {
            Ln.d("No referrer string, bail", new Object[0]);
            return;
        }
        Bundle urlDecode = UriUtils.urlDecode(string);
        Ln.d("referrerParts is %s", urlDecode);
        String strings = Strings.toString(urlDecode.getString("utm_campaign"));
        String strings2 = Strings.toString(urlDecode.getString("utm_source"));
        String strings3 = Strings.toString(urlDecode.getString("utm_medium"));
        String strings4 = Strings.toString(urlDecode.getString(UtmParams.UTM_CONTENT));
        String strings5 = Strings.toString(urlDecode.getString(UtmParams.UTM_TERM));
        Ln.d("Referral: campaign = %s, source = %s, medium = %s, content = %s, term = %s", strings, strings2, strings3, strings4, strings5);
        onReceivedReferral(context, strings, strings2, strings3, strings4, strings5);
    }
}
