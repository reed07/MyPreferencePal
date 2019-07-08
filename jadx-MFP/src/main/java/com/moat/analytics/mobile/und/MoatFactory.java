package com.moat.analytics.mobile.und;

import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.moat.analytics.mobile.und.v.b;
import java.util.Map;

public abstract class MoatFactory {
    public static MoatFactory create() {
        try {
            return new n();
        } catch (Exception e) {
            m.a(e);
            return new b();
        }
    }

    public abstract <T> T createCustomTracker(MoatPlugin<T> moatPlugin);

    public abstract NativeDisplayTracker createNativeDisplayTracker(View view, Map<String, String> map);

    public abstract NativeVideoTracker createNativeVideoTracker(String str);

    public abstract WebAdTracker createWebAdTracker(ViewGroup viewGroup);

    public abstract WebAdTracker createWebAdTracker(WebView webView);
}
