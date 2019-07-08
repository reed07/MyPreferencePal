package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.webkit.WebView;
import com.facebook.ads.AudienceNetworkActivity;
import com.google.ads.interactivemedia.v3.api.CompanionAdSlot.ClickListener;
import com.google.ads.interactivemedia.v3.impl.data.CompanionData;
import com.google.ads.interactivemedia.v3.impl.data.CompanionData.a;
import java.util.List;

/* compiled from: IMASDK */
public final class acx extends WebView {
    public acx(Context context, aeb aeb, CompanionData companionData, List<ClickListener> list) {
        super(context);
        getSettings().setJavaScriptEnabled(true);
        getSettings().setSupportMultipleWindows(true);
        setWebChromeClient(new acy(context, aeb, list));
        if (companionData.type() == a.Html) {
            loadData(companionData.src(), AudienceNetworkActivity.WEBVIEW_MIME_TYPE, null);
        } else if (companionData.type() == a.IFrame) {
            loadUrl(companionData.src());
        } else {
            String valueOf = String.valueOf(companionData.type());
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 51);
            sb.append("Companion type ");
            sb.append(valueOf);
            sb.append(" is not valid for a CompanionWebView");
            throw new IllegalArgumentException(sb.toString());
        }
    }
}
