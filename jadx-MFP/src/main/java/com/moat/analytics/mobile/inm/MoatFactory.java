package com.moat.analytics.mobile.inm;

import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.moat.analytics.mobile.inm.v.b;
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

    @UiThread
    public abstract <T> T createCustomTracker(MoatPlugin<T> moatPlugin);

    @UiThread
    public abstract NativeDisplayTracker createNativeDisplayTracker(@NonNull View view, @NonNull Map<String, String> map);

    @UiThread
    public abstract NativeVideoTracker createNativeVideoTracker(String str);

    @UiThread
    public abstract WebAdTracker createWebAdTracker(@NonNull ViewGroup viewGroup);

    @UiThread
    public abstract WebAdTracker createWebAdTracker(@NonNull WebView webView);
}
