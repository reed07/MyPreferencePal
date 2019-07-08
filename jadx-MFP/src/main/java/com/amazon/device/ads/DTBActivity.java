package com.amazon.device.ads;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.mopub.common.Constants;
import java.util.List;

public class DTBActivity extends Activity {
    private final String LOG_TAG = DTBActivity.class.getSimpleName();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (intent == null || intent.getAction() == null || !intent.getAction().equals("android.intent.action.VIEW") || intent.getDataString() == null) {
            DtbLog.warn("Invalid intent has been received, please debug for more information.");
        } else {
            String dataString = intent.getDataString();
            if (dataString.startsWith("amazonmobile")) {
                DtbLog.info("Received a request to open amazonmobile url.");
                execute(dataString);
            } else {
                DtbLog.info("Received a request to open unknown url.");
                DtbCommonUtils.launchActivityForIntentLink(dataString, getApplicationContext());
            }
        }
        finish();
    }

    /* access modifiers changed from: 0000 */
    public boolean isWindowshopPresent(Context context) {
        return context.getPackageManager().getLaunchIntentForPackage("com.amazon.windowshop") != null;
    }

    /* access modifiers changed from: 0000 */
    public void launchWindowshopDetailPage(Context context, String str) {
        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage("com.amazon.windowshop");
        if (launchIntentForPackage != null) {
            launchIntentForPackage.putExtra("com.amazon.windowshop.refinement.asin", str);
            context.startActivity(launchIntentForPackage);
        }
    }

    public void execute(String str) {
        List<String> list;
        DtbLog.debug("Executing AmazonMobile Intent");
        Uri parse = Uri.parse(str);
        try {
            list = parse.getQueryParameters(Constants.INTENT_SCHEME);
        } catch (UnsupportedOperationException unused) {
            list = null;
        }
        if (list != null && list.size() > 0) {
            for (String launchActivityForIntentLink : list) {
                if (DtbCommonUtils.launchActivityForIntentLink(launchActivityForIntentLink, getApplicationContext())) {
                    return;
                }
            }
            String str2 = this.LOG_TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("Special url clicked, but was not handled by SDK. Url: ");
            sb.append(parse.toString());
            DtbLog.info(str2, sb.toString());
        } else if (!isWindowshopPresent(getApplicationContext())) {
            String str3 = this.LOG_TAG;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Special url clicked, but was not handled by SDK. Url: ");
            sb2.append(parse.toString());
            DtbLog.info(str3, sb2.toString());
        } else if (parse.getHost().equals("shopping")) {
            String queryParameter = parse.getQueryParameter("app-action");
            if (queryParameter != null && queryParameter.length() != 0) {
                if (queryParameter.equals(ProductAction.ACTION_DETAIL)) {
                    String queryParameter2 = parse.getQueryParameter("asin");
                    if (queryParameter2 != null && queryParameter2.length() != 0) {
                        launchWindowshopDetailPage(getApplicationContext(), queryParameter2);
                    }
                } else if (queryParameter.equals("search")) {
                    String queryParameter3 = parse.getQueryParameter("keyword");
                    if (queryParameter3 != null && queryParameter3.length() != 0) {
                        launchWindowshopSearchPage(getApplicationContext(), queryParameter3);
                    }
                } else if (queryParameter.equals("webview")) {
                    String str4 = this.LOG_TAG;
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("Special url clicked, but was not handled by SDK. Url: ");
                    sb3.append(parse.toString());
                    DtbLog.info(str4, sb3.toString());
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void launchWindowshopSearchPage(Context context, String str) {
        Intent intent = new Intent("android.intent.action.SEARCH");
        intent.setComponent(new ComponentName("com.amazon.windowshop", "com.amazon.windowshop.search.SearchResultsGridActivity"));
        intent.putExtra("query", str);
        try {
            context.startActivity(intent);
        } catch (RuntimeException unused) {
        }
    }
}
