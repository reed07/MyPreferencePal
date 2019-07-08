package com.moat.analytics.mobile.und;

import android.app.Activity;
import android.app.Application;
import android.media.MediaPlayer;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.moat.analytics.mobile.und.NativeDisplayTracker.MoatUserInteractionType;
import java.util.Map;

abstract class v {

    public static class a extends MoatAnalytics {
        public void prepareNativeDisplayTracking(String str) {
        }

        public void start(Application application) {
        }

        public void start(MoatOptions moatOptions, Application application) {
        }
    }

    public static class b extends MoatFactory {
        public <T> T createCustomTracker(MoatPlugin<T> moatPlugin) {
            return null;
        }

        public NativeDisplayTracker createNativeDisplayTracker(View view, Map<String, String> map) {
            return new c();
        }

        public NativeVideoTracker createNativeVideoTracker(String str) {
            return new d();
        }

        public WebAdTracker createWebAdTracker(ViewGroup viewGroup) {
            return new e();
        }

        public WebAdTracker createWebAdTracker(WebView webView) {
            return new e();
        }
    }

    static class c implements NativeDisplayTracker {
        c() {
        }

        public void reportUserInteractionEvent(MoatUserInteractionType moatUserInteractionType) {
        }

        public void setActivity(Activity activity) {
        }

        public void startTracking() {
        }

        public void stopTracking() {
        }
    }

    static class d implements NativeVideoTracker {
        d() {
        }

        public void changeTargetView(View view) {
        }

        public void dispatchEvent(MoatAdEvent moatAdEvent) {
        }

        public void setActivity(Activity activity) {
        }

        public void setPlayerVolume(Double d) {
        }

        public void stopTracking() {
        }

        public boolean trackVideoAd(Map<String, String> map, MediaPlayer mediaPlayer, View view) {
            return false;
        }
    }

    static class e implements WebAdTracker {
        e() {
        }

        public void setActivity(Activity activity) {
        }

        public void startTracking() {
        }

        public void stopTracking() {
        }
    }

    v() {
    }
}
