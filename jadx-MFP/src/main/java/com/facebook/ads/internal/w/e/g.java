package com.facebook.ads.internal.w.e;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build.VERSION;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.r.a;
import com.facebook.ads.internal.settings.AdInternalSettings;
import com.facebook.ads.internal.settings.a.C0009a;
import com.google.android.exoplayer2.C;
import com.myfitnesspal.shared.constants.Constants;
import java.util.Iterator;

public class g {
    public static void a(g gVar, Context context, Uri uri, String str) {
        boolean z = a(uri.getScheme()) && uri.getHost().equals(Constants.Uri.GOOGLE_PLAY);
        if (uri.getScheme().equals("market") || z) {
            try {
                gVar.a(context, uri);
                return;
            } catch (c unused) {
            }
        }
        gVar.a(context, uri, str);
    }

    private static boolean a(String str) {
        return com.mopub.common.Constants.HTTP.equalsIgnoreCase(str) || com.mopub.common.Constants.HTTPS.equalsIgnoreCase(str);
    }

    private void b(Context context, Uri uri) {
        context.startActivity(c(context, uri));
    }

    private Intent c(Context context, Uri uri) {
        Intent intent = new Intent("android.intent.action.VIEW", uri);
        intent.setComponent(null);
        if (VERSION.SDK_INT >= 15) {
            intent.setSelector(null);
        }
        intent.addCategory("android.intent.category.BROWSABLE");
        intent.addFlags(C.ENCODING_PCM_MU_LAW);
        intent.putExtra("com.android.browser.application_id", context.getPackageName());
        intent.putExtra("create_new_tab", false);
        return intent;
    }

    public void a(Context context, Uri uri) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/"));
        boolean z = false;
        Iterator it = context.getPackageManager().queryIntentActivities(intent, 0).iterator();
        while (true) {
            if (it.hasNext()) {
                if (((ResolveInfo) it.next()).activityInfo.applicationInfo.packageName.equals("com.android.vending")) {
                    z = true;
                    break;
                }
            } else {
                break;
            }
        }
        if (z) {
            Intent c = c(context, uri);
            c.setPackage("com.android.vending");
            context.startActivity(c);
            return;
        }
        throw new c();
    }

    public void a(Context context, Uri uri, String str) {
        if (a(uri.getScheme()) && a.o(context)) {
            Intent intent = new Intent();
            intent.setClassName(context.getPackageName(), AdInternalSettings.d ? "com.facebook.ads.internal.ipc.RemoteANActivity" : "com.facebook.ads.AudienceNetworkActivity");
            intent.addFlags(C.ENCODING_PCM_MU_LAW);
            intent.putExtra(AudienceNetworkActivity.VIEW_TYPE, C0009a.BROWSER);
            intent.putExtra(AudienceNetworkActivity.BROWSER_URL, uri.toString());
            intent.putExtra(AudienceNetworkActivity.CLIENT_TOKEN, str);
            intent.putExtra(AudienceNetworkActivity.HANDLER_TIME, System.currentTimeMillis());
            try {
                context.startActivity(intent);
                return;
            } catch (ActivityNotFoundException unused) {
                intent.setClassName(context.getPackageName(), "com.facebook.ads.InterstitialAdActivity");
                try {
                    context.startActivity(intent);
                    return;
                } catch (ActivityNotFoundException unused2) {
                }
            }
        }
        b(context, uri);
    }
}
